package api.controllers;

import cashregister.domain.entities.Transaction;
import cashregister.domain.repositories.interfaces.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static cashregister.domain.usecases.PresentTransaction.presentTransaction;

@RestController
public class ShowTransactionController extends UseCaseController {
    @Autowired
    private TransactionRepository transactionRepository;
    private Transaction presentedTransaction;

    @GetMapping("/transactions/{transactionId}")
    public Transaction showTransaction(@PathVariable String transactionId){
        return presentTransaction(transactionId, transactionRepository);
    }
}