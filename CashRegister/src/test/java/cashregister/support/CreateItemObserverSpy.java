package cashregister.support;

import cashregister.domain.usecases.CreateItemObserver;
import cashregister.domain.values.ValidationError;

import java.util.ArrayList;
import java.util.List;

public class CreateItemObserverSpy implements CreateItemObserver {
    private List<ValidationError> spyValidationErrors;
    private Object spyCreatedItemId;

    public List<ValidationError> spyValidationErrors() {
        return spyValidationErrors;
    }

    @Override
    public void validationFailed(List<ValidationError> errors) {
        spyValidationErrors = errors;
    }

    @Override
    public void itemCreated(Object id) {
        spyCreatedItemId = id;
    }

    public Object spyCreatedItemId() {
        return spyCreatedItemId;
    }
}
