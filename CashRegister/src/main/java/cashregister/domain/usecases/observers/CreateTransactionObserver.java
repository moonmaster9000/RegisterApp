package cashregister.domain.usecases.observers;

import cashregister.domain.entities.Transaction;

public interface CreateTransactionObserver {
    void transactionCreated(Transaction transaction);
}
