package api.controllers;

import cashregister.domain.entities.Transaction;
import cashregister.domain.repositories.interfaces.ItemRepository;
import cashregister.domain.repositories.interfaces.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static cashregister.domain.usecases.AddItemToTransaction.addItemToTransaction;

@RestController
public class AddItemToTransactionController extends UseCaseController  {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private ItemRepository itemRepository;

    @PostMapping("/transactions/{transactionId}/items/{itemBarcode}")
    @ResponseStatus(HttpStatus.CREATED)
    public Transaction create(@PathVariable String transactionId, @PathVariable String itemBarcode) {
        return addItemToTransaction(itemBarcode, transactionId, transactionRepository, itemRepository);
    }
}