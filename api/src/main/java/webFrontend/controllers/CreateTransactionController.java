package webFrontend.controllers;

import cashregister.domain.entities.Transaction;
import cashregister.domain.repositories.interfaces.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static cashregister.domain.usecases.CreateTransaction.createTransaction;

@RestController
public class CreateTransactionController {
    @Autowired
    private TransactionRepository repo;
    private Transaction createdTransaction;

    @PostMapping("/transactions")
    public Transaction create() {
        return createTransaction(repo);
    }
}