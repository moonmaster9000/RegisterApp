package cashregister.domain.usecases;

import cashregister.domain.entities.Item;
import cashregister.domain.repositories.interfaces.ItemRepository;
import cashregister.domain.usecases.exceptions.InvalidRequest;

public class CreateItem {
    private final Item item;
    private final ItemRepository itemRepo;

    public CreateItem(Item item, ItemRepository itemRepo) {
        this.item = item;
        this.itemRepo = itemRepo;
    }

    public Item execute() {
        if (!item.isValid())
            throw new InvalidRequest(item.getValidationErrors());

        itemRepo.save(item);
        return item;
    }

    public static Item createItem(Item item, ItemRepository itemRepo) {
        return new CreateItem(item, itemRepo).execute();
    }
}
