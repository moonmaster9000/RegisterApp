package webFrontend.controllers;

import cashregister.domain.entities.Item;
import cashregister.domain.repositories.interfaces.ItemRepository;
import cashregister.domain.usecases.observers.CreateItemObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static cashregister.domain.usecases.CreateItem.createItem;

@RestController
public class CreateItemController extends UseCaseController implements CreateItemObserver {
    @Autowired
    private ItemRepository itemRepo;
    private Item createdItem;

    @PostMapping("/items")
    public Item create(@RequestBody Item item) {
        createItem(item, this, itemRepo);
        return getCreatedItem();
    }

    @Override
    public void itemCreated(Item item) {
        createdItem = item;
    }

    private Item getCreatedItem() {
        return createdItem;
    }
}