package cashregister.domain.usecases;

import cashregister.domain.entities.Item;
import cashregister.domain.entities.Transaction;
import cashregister.domain.repositories.interfaces.ItemRepository;
import cashregister.domain.repositories.interfaces.TransactionRepository;
import cashregister.domain.values.ValidationError;

import java.util.ArrayList;
import java.util.List;

import static cashregister.domain.Constraint.EXISTS;

public class AddItemToTransaction {
    private final String barcode;
    private final TransactionRepository transactionRepo;
    private final ItemRepository itemRepo;
    private String transactionId;

    public AddItemToTransaction(String barcode, String transactionId, TransactionRepository transactionRepo, ItemRepository itemRepo) {
        this.barcode = barcode;
        this.transactionId = transactionId;
        this.transactionRepo = transactionRepo;
        this.itemRepo = itemRepo;
    }

    public Transaction execute() {
        return new Execute().invoke();
    }

    public static Transaction addItemToTransaction(String barcode, String transactionId, TransactionRepository transactionRepo, ItemRepository itemRepo) {
        return new AddItemToTransaction(barcode, transactionId, transactionRepo, itemRepo).execute();
    }

    private class Execute {

        private final List<ValidationError> errors;
        private final Transaction transaction;
        private final Item item;

        Execute() {
            errors = new ArrayList<>();
            transaction = transactionRepo.findById(transactionId);
            item = itemRepo.findByBarcode(barcode);
        }

        Transaction invoke() {
            validate();

            if (invalid())
                throw invalidRequestWithValidationErrors();

            addItem();
            return transaction;
        }

        private InvalidRequest invalidRequestWithValidationErrors() {
            return new InvalidRequest(errors);
        }

        private void addItem() {
            transaction.addItem(item);
            transactionRepo.save(transaction);
        }

        private boolean invalid() {
            return !errors.isEmpty();
        }

        private void validate() {
            if (transaction == null) {
                errors.add(new ValidationError("transactionId", EXISTS));
            }

            if (item == null) {
                errors.add(new ValidationError("barcode", EXISTS));
            }
        }
    }
}
