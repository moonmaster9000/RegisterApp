package webFrontend.controllers;

import cashregister.domain.entities.Transaction;
import cashregister.domain.repositories.interfaces.TransactionRepository;
import cashregister.domain.usecases.CreateTransaction;
import cashregister.domain.usecases.observers.CreateTransactionObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateTransactionController implements CreateTransactionObserver {
    @Autowired
    private TransactionRepository repo;
    private Transaction createdTransaction;

    @PostMapping("/transactions")
    public Transaction create() {
        new CreateTransaction(this, repo).execute();
        return getCreatedTransaction();
    }

    private Transaction getCreatedTransaction() {
        return createdTransaction;
    }

    @Override
    public void transactionCreated(Transaction transaction) {
        createdTransaction = transaction;
    }
}