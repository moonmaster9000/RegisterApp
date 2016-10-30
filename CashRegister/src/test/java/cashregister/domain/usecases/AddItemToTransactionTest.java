package cashregister.domain.usecases;

import cashregister.domain.entities.Item;
import cashregister.domain.entities.Transaction;
import cashregister.domain.repositories.interfaces.ItemRepository;
import cashregister.domain.repositories.interfaces.TransactionRepository;
import cashregister.domain.values.ValidationError;
import cashregister.support.FakeItemRepository;
import cashregister.support.FakeTransactionRepository;
import org.junit.Before;
import org.junit.Test;

import static cashregister.domain.Constraint.EXISTS;
import static cashregister.domain.usecases.AddItemToTransaction.addItemToTransaction;
import static cashregister.domain.usecases.CreateItem.createItem;
import static cashregister.domain.usecases.CreateTransaction.createTransaction;
import static cashregister.tests.usecases.UseCaseAssertions.assertThrowsValidationError;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddItemToTransactionTest {

    @Before
    public void before() {
        createItem(item, itemRepo);
        transaction = createTransaction(transactionRepo);
    }

    @Test
    public void whenTheTransactionIdDoesntExist() {
        assertThrowsValidationError(
            () -> addItemToTransaction(item.getBarcode(), "unknown transaction id", transactionRepo, itemRepo),
            new ValidationError("transactionId", EXISTS)
        );
    }

    @Test
    public void whenTheBarcodeMapsToAnUnknownItem() {
        assertThrowsValidationError(
            () -> addItemToTransaction(item.getBarcode(), "unknown transaction id", transactionRepo, itemRepo),
            new ValidationError("transactionId", EXISTS)
        );
    }

    @Test
    public void whenValid_TransactionSentBackToObserverWithItem() {
        Transaction updatedTransaction = addItemToTransaction(item.getBarcode(), transaction.getId(), transactionRepo, itemRepo);

        assertThat(updatedTransaction.getItems(), hasItem(item));
    }

    private TransactionRepository transactionRepo = new FakeTransactionRepository();
    private ItemRepository itemRepo = new FakeItemRepository();
    private Transaction transaction;


    private String validBarcode = "valid barcode";
    private String validDisplayName = "valid display name";
    private int validPriceInCents = 1;

    private Item item = new Item(validDisplayName, validBarcode, validPriceInCents);
}