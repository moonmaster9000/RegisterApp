package webFrontend.controllers;

import cashregister.domain.entities.Item;
import cashregister.domain.repositories.interfaces.ItemRepository;
import cashregister.domain.usecases.CreateItem;
import cashregister.domain.usecases.CreateItemObserver;
import cashregister.domain.values.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

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
        ApiCreateItemObserver itemObserver = new ApiCreateItemObserver();
        CreateItem.createItem(item, itemObserver, itemRepo);
        return itemObserver.getCreatedItem();
    }

    @ResponseStatus(UNPROCESSABLE_ENTITY)
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

