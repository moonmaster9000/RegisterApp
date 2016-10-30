package api.controllers;

import cashregister.domain.entities.Item;
import cashregister.domain.repositories.interfaces.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static cashregister.domain.usecases.CreateItem.createItem;

@RestController
public class CreateItemController extends UseCaseController {
    @Autowired
    private ItemRepository itemRepo;

    @PostMapping("/items")
    @ResponseStatus(HttpStatus.CREATED)
    public Item create(@RequestBody Item item) {
        return createItem(item, itemRepo);
    }
}