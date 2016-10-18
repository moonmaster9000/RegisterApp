package cashregister.support;

import cashregister.domain.entities.Transaction;
import cashregister.domain.usecases.observers.CreateTransactionObserver;

public class CreateTransactionObserverSpy implements CreateTransactionObserver {
    private Transaction spyCreatedTransaction;

    public Transaction spyCreatedTransaction() {
        return spyCreatedTransaction;
    }

    @Override
    public void transactionCreated(Transaction transaction) {
        spyCreatedTransaction = transaction;
    }
}
