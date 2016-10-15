package cashregister.domain.entities;

import cashregister.domain.repositories.interfaces.ItemRepository;
import cashregister.domain.usecases.CreateItemObserver;

public class CreateItem {
    public static void createItem(UserControlledItemAttributes attrs, CreateItemObserver observer, ItemRepository itemRepo) {
        Item item = (Item) attrs;

        if (!item.isValid())
            observer.validationFailed(item.getValidationErrors());

        else {
            itemRepo.save(item);
            observer.itemCreated(item);
        }
    }
}
