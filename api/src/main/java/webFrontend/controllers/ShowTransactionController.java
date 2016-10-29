package webFrontend.controllers;

import cashregister.domain.entities.Transaction;
import cashregister.domain.repositories.interfaces.TransactionRepository;
import cashregister.domain.usecases.observers.PresentTransactionObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static cashregister.domain.usecases.PresentTransaction.presentTransaction;

@RestController
public class ShowTransactionController extends UseCaseController implements PresentTransactionObserver {
    @Autowired
    private TransactionRepository transactionRepository;
    private Transaction presentedTransaction;

    @GetMapping("/transactions/{transactionId}")
    public Transaction showTransaction(@PathVariable String transactionId){
        presentTransaction(transactionId, this, transactionRepository);
        return getPresentedTransaction();
    }

    private Transaction getPresentedTransaction() {
        return presentedTransaction;
    }

    @Override
    public void transactionPresented(Transaction transaction) {
        presentedTransaction = transaction;
    }
}