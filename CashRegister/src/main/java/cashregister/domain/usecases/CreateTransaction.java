package cashregister.domain.usecases;

import cashregister.domain.entities.Transaction;
import cashregister.domain.repositories.interfaces.TransactionRepository;

public class CreateTransaction {
    private Transaction transaction;
    private final TransactionRepository repo;

    public CreateTransaction(Transaction transaction, TransactionRepository repo) {
        setTransaction(transaction);
        this.repo = repo;
    }

    public Transaction execute() {
        repo.save(transaction);
        return transaction;
    }

    private void setTransaction(Transaction transaction) {
        if (transaction == null){
            this.transaction = new Transaction();
        } else {
            this.transaction = transaction;
        }
    }

    public static Transaction createTransaction(TransactionRepository transactionRepo) {
        return new CreateTransaction(new Transaction(), transactionRepo).execute();
    }

    public static Transaction createTransaction(Transaction transaction, TransactionRepository transactionRepo) {
        return new CreateTransaction(transaction, transactionRepo).execute();
    }
}
