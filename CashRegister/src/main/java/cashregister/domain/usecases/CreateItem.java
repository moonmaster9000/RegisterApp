package cashregister.domain.usecases;

import cashregister.domain.entities.Item;
import cashregister.domain.repositories.interfaces.ItemRepository;
import cashregister.domain.values.ValidationError;

import java.util.ArrayList;

import static cashregister.domain.Constraint.POSITIVE;
import static cashregister.domain.Constraint.REQUIRED;

public class CreateItem {
    public static void createItem(String displayName, String barcode, int priceInCents, CreateItemObserver observer, ItemRepository itemRepo) {
        Item item = new Item(displayName, barcode, priceInCents);

        if (!item.isValid())
            observer.validationFailed(item.getValidationErrors());

        else {
            itemRepo.save(item);
            observer.itemCreated(item.getId());
        }
    }
}
