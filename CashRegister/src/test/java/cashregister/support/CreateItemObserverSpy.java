package cashregister.support;

import cashregister.domain.entities.PresentableItemAttributes;
import cashregister.domain.usecases.CreateItemObserver;
import cashregister.domain.values.ValidationError;

import java.util.List;

public class CreateItemObserverSpy implements CreateItemObserver {
    private List<ValidationError> spyValidationErrors;
    private PresentableItemAttributes spyCreatedItemAttrs;

    public List<ValidationError> spyValidationErrors() {
        return spyValidationErrors;
    }

    @Override
    public void validationFailed(List<ValidationError> errors) {
        spyValidationErrors = errors;
    }

    @Override
    public void itemCreated(PresentableItemAttributes attrs) {
        spyCreatedItemAttrs = attrs;
    }

    public Object spyCreatedItemId() {
        return spyCreatedItemAttrs.getId();
    }
}
