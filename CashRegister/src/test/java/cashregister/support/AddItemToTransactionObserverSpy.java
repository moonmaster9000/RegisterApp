package cashregister.support;

import cashregister.domain.entities.Transaction;
import cashregister.domain.usecases.observers.AddItemToTransactionObserver;
import cashregister.domain.values.ValidationError;

import java.util.ArrayList;
import java.util.List;

public class AddItemToTransactionObserverSpy implements AddItemToTransactionObserver {
    private List<ValidationError> spyValidationErrors = new ArrayList<>();
    private Transaction spyCreatedTransaction;

    public List<ValidationError> spyValidationErrors() {
        return spyValidationErrors;
    }

    @Override
    public void validationFailed(List<ValidationError> errors) {
        spyValidationErrors = errors;
    }

    @Override
    public void itemAddedToTransaction(Transaction transaction) {
        spyCreatedTransaction = transaction;
    }

    public Transaction spyCreatedTransaction() {
        return spyCreatedTransaction;
    }
}
