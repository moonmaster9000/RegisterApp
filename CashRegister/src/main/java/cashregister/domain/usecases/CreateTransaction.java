package cashregister.domain.usecases;

import cashregister.domain.entities.Transaction;
import cashregister.domain.repositories.interfaces.TransactionRepository;
import cashregister.domain.usecases.observers.CreateTransactionObserver;

public class CreateTransaction {
    private final CreateTransactionObserver observer;
    private final TransactionRepository repo;

    public CreateTransaction(CreateTransactionObserver observer, TransactionRepository repo) {
        this.observer = observer;
        this.repo = repo;
    }

    public void execute() {
        Transaction transaction = new Transaction();
        repo.save(transaction);
        observer.transactionCreated(transaction);
    }

    public static void createTransaction(CreateTransactionObserver createTransactionObserver, TransactionRepository transactionRepo) {
        new CreateTransaction(createTransactionObserver, transactionRepo).execute();
    }
}
