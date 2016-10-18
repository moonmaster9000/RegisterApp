package cashregister.support;

import cashregister.domain.entities.Transaction;
import cashregister.domain.usecases.observers.PresentTransactionObserver;
import cashregister.domain.values.ValidationError;

import java.util.List;

public class PresentTransactionObserverSpy implements PresentTransactionObserver {

    private Transaction spyPresentedTransaction;
    private List<ValidationError> spyValidationErrors;

    public Transaction spyPresentedTransaction() {
        return spyPresentedTransaction;
    }

    public List<ValidationError> spyValidationErrors() {
        return spyValidationErrors;
    }

    @Override
    public void validationFailed(List<ValidationError> errors) {
        spyValidationErrors = errors;
    }

    @Override
    public void transactionPresented(Transaction transaction) {
        spyPresentedTransaction = transaction;
    }
}
