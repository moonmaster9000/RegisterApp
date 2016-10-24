package webFrontend.controllers;

import cashregister.domain.entities.Transaction;
import cashregister.domain.repositories.interfaces.ItemRepository;
import cashregister.domain.repositories.interfaces.TransactionRepository;
import cashregister.domain.usecases.AddItemToTransaction;
import cashregister.domain.usecases.observers.AddItemToTransactionObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddItemToTransactionController extends UseCaseController implements AddItemToTransactionObserver  {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ItemRepository itemRepository;

    private Transaction updatedTransaction;

    @PostMapping("/transactions/{transactionId}/items/{itemId}")
    public Transaction create(@PathVariable String transactionId, @PathVariable String itemId) {
        new AddItemToTransaction(itemId, transactionId, transactionRepository, itemRepository, this).execute();
        return getUpdatedTransaction();
    }

    @Override
    public void itemAddedToTransaction(Transaction transaction) {
        updatedTransaction = transaction;
    }

    public Transaction getUpdatedTransaction() {
        return updatedTransaction;
    }
}