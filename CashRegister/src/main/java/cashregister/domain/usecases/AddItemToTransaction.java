package cashregister.domain.usecases;

import cashregister.domain.entities.Item;
import cashregister.domain.entities.Transaction;
import cashregister.domain.repositories.interfaces.ItemRepository;
import cashregister.domain.repositories.interfaces.TransactionRepository;
import cashregister.domain.usecases.observers.AddItemToTransactionObserver;
import cashregister.domain.values.ValidationError;

import java.util.ArrayList;
import java.util.List;

import static cashregister.domain.Constraint.EXISTS;

public class AddItemToTransaction {
    private final String barcode;
    private final AddItemToTransactionObserver observer;
    private final TransactionRepository transactionRepo;
    private final ItemRepository itemRepo;
    private String transactionId;

    public AddItemToTransaction(String barcode, String transactionId, TransactionRepository transactionRepo, ItemRepository itemRepo, AddItemToTransactionObserver observer) {
        this.barcode = barcode;
        this.transactionId = transactionId;
        this.observer = observer;
        this.transactionRepo = transactionRepo;
        this.itemRepo = itemRepo;
    }

    public void execute() {
        new Execute().invoke();
    }

    public static void addItemToTransaction(String barcode, String transactionId, TransactionRepository transactionRepo, ItemRepository itemRepo, AddItemToTransactionObserver observer) {
        new AddItemToTransaction(barcode, transactionId, transactionRepo, itemRepo, observer).execute();
    }

    private class Execute {

        private final List<ValidationError> errors;
        private final Transaction transaction;
        private final Item item;

        public Execute() {
            errors = new ArrayList<>();
            transaction = transactionRepo.findById(transactionId);
            item = itemRepo.findByBarcode(barcode);
        }

        public void invoke() {
            validate();

            if (invalid()) {
                sendValidationErrorsToObserver();
            } else {
                addItem();
                sendTransactionToObserver();
            }
        }

        private void sendTransactionToObserver() {
            observer.itemAddedToTransaction(transaction);
        }

        private void addItem() {
            transaction.addItem(item);
            transactionRepo.save(transaction);
        }

        private void sendValidationErrorsToObserver() {
            observer.validationFailed(errors);
        }

        private boolean invalid() {
            return !errors.isEmpty();
        }

        private void validate() {
            if (transaction == null){
                errors.add(new ValidationError("transactionId", EXISTS));
            }

            if (item == null){
                errors.add(new ValidationError("barcode", EXISTS));
            }
        }
    }
}
