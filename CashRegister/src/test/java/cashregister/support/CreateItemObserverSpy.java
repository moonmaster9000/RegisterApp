package cashregister.support;

import cashregister.domain.entities.Item;
import cashregister.domain.usecases.observers.CreateItemObserver;
import cashregister.domain.values.ValidationError;

import java.util.List;

public class CreateItemObserverSpy implements CreateItemObserver {
    private List<ValidationError> spyValidationErrors;
    private Item spyCreatedItem;

    public List<ValidationError> spyValidationErrors() {
        return spyValidationErrors;
    }

    @Override
    public void validationFailed(List<ValidationError> errors) {
        spyValidationErrors = errors;
    }

    @Override
    public void itemCreated(Item item) {
        spyCreatedItem = item;
    }

    public Object spyCreatedItemId() {
        return spyCreatedItem.getId();
    }

    public Item spyCreatedItem() {
        return spyCreatedItem;
    }
}
