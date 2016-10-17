package webFrontend.controllers;

import cashregister.domain.entities.Item;
import cashregister.domain.repositories.interfaces.ItemRepository;
import cashregister.domain.usecases.CreateItem;
import cashregister.domain.usecases.CreateItemObserver;
import cashregister.domain.values.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

class InvalidRequestException extends RuntimeException {
    private List<ValidationError> errors;

    public InvalidRequestException(List<ValidationError> errors) {
        this.errors = errors;
    }

    public List<ValidationError> getErrors() {
        return errors;
    }
}

@RestController
public class ItemsController {
    @Autowired
    private ItemRepository itemRepo;

    @PostMapping("/items")
    public Item create(@RequestBody Item item){
        return createItem(item, itemRepo);
    }

    private Item createItem(Item item, ItemRepository itemRepo) {
        ApiCreateItemObserver itemObserver = new ApiCreateItemObserver();
        new CreateItem(item, itemObserver, itemRepo).execute();
        return itemObserver.getCreatedItem();
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(InvalidRequestException.class)
    public List<ValidationError> handleInvalidRequest(InvalidRequestException invalidRequest){
        return invalidRequest.getErrors();
    }
}

class ApiCreateItemObserver implements CreateItemObserver {
    private Item createdItem;

    @Override
    public void validationFailed(List<ValidationError> errors) {
        throw new InvalidRequestException(errors);
    }

    @Override
    public void itemCreated(Item item) {
        createdItem = item;
    }

    public Item getCreatedItem() {
        return createdItem;
    }
}

