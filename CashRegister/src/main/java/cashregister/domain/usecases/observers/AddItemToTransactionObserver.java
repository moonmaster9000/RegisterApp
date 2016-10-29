package cashregister.domain.usecases.observers;

import cashregister.domain.entities.Transaction;
import cashregister.domain.values.ValidationError;

import java.util.List;

public interface AddItemToTransactionObserver {
    void validationFailed(List<ValidationError> errors);

    void itemAddedToTransaction(Transaction transaction);
}
