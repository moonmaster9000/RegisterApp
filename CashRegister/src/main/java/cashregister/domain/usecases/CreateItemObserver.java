package cashregister.domain.usecases;

import cashregister.domain.values.ValidationError;

import java.util.ArrayList;
import java.util.List;

public interface CreateItemObserver {
    void validationFailed(List<ValidationError> errors);

    void itemCreated(Object id);
}
