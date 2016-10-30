package cashregister.domain.usecases;

import cashregister.domain.entities.Transaction;
import cashregister.domain.repositories.interfaces.TransactionRepository;

public class CreateTransaction {
    private final TransactionRepository repo;

    public CreateTransaction(TransactionRepository repo) {
        this.repo = repo;
    }

    public Transaction execute() {
        Transaction transaction = new Transaction();
        repo.save(transaction);
        return transaction;
    }

    public static Transaction createTransaction(TransactionRepository transactionRepo) {
        return new CreateTransaction(transactionRepo).execute();
    }
}
