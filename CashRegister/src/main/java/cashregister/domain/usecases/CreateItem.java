package cashregister.domain.usecases;

import cashregister.domain.entities.Item;
import cashregister.domain.repositories.interfaces.ItemRepository;
import cashregister.domain.usecases.observers.CreateItemObserver;

public class CreateItem {
    private final Item item;
    private final CreateItemObserver observer;
    private final ItemRepository itemRepo;

    public CreateItem(Item item, CreateItemObserver observer, ItemRepository itemRepo) {
        this.item = item;
        this.observer = observer;
        this.itemRepo = itemRepo;
    }

    public void execute(){
        if (!item.isValid())
            observer.validationFailed(item.getValidationErrors());

        else {
            itemRepo.save(item);
            observer.itemCreated(item);
        }
    }

    public static void createItem(Item item, CreateItemObserver createItemObserver, ItemRepository itemRepo) {
        new CreateItem(item, createItemObserver, itemRepo).execute();
    }
}
