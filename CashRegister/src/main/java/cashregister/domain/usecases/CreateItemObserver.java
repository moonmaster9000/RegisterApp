package cashregister.domain.usecases;

import cashregister.domain.entities.Item;
import cashregister.domain.values.ValidationError;

import java.util.List;

public interface CreateItemObserver {
    void validationFailed(List<ValidationError> errors);

    void itemCreated(Item item);
}
