package cashregister.domain.usecases;

import cashregister.domain.values.ValidationError;

import java.util.ArrayList;

public interface CreateItemObserver {
    void validationFailed(ArrayList<ValidationError> errors);
}
