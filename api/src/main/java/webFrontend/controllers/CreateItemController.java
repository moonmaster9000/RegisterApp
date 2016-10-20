package webFrontend.controllers;

import cashregister.domain.entities.Item;
import cashregister.domain.repositories.interfaces.ItemRepository;
import cashregister.domain.usecases.CreateItem;
import cashregister.domain.usecases.observers.CreateItemObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateItemController extends UseCaseController implements CreateItemObserver {
    @Autowired
    private ItemRepository itemRepo;
    private Item createdItem;

    @PostMapping("/items")
    public Item create(@RequestBody Item item) {
        return createItem(item, itemRepo);
    }

    private Item createItem(Item item, ItemRepository itemRepo) {
        new CreateItem(item, this, itemRepo).execute();
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