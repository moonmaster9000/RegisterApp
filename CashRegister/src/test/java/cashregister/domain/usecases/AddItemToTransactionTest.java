package cashregister.domain.usecases;

import cashregister.domain.entities.Item;
import cashregister.domain.entities.Transaction;
import cashregister.domain.repositories.interfaces.ItemRepository;
import cashregister.domain.repositories.interfaces.TransactionRepository;
import cashregister.domain.values.ValidationError;
import cashregister.support.*;
import org.junit.Before;
import org.junit.Test;

import static cashregister.domain.Constraint.EXISTS;
import static cashregister.domain.usecases.AddItemToTransaction.addItemToTransaction;
import static cashregister.domain.usecases.CreateItem.createItem;
import static cashregister.domain.usecases.CreateTransaction.createTransaction;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class AddItemToTransactionTest {
    @Before
    public void before() {
        createItem(item, createItemObserver, itemRepo);
        createTransaction(createTransactionObserver, transactionRepo);
    }

    @Test
    public void whenTheTransactionIdDoesntExist() {
        addItemToTransaction(item.getBarcode(), "unknown transaction id", transactionRepo, itemRepo, observer);

        assertThat(observer.spyValidationErrors(), hasItem(new ValidationError("transactionId", EXISTS)));
    }

    @Test
    public void whenTheBarcodeMapsToAnUnknownItem() {
        addItemToTransaction("unknown barcode", createdTransaction().getId(), transactionRepo, itemRepo, observer);

        assertThat(observer.spyValidationErrors(), hasItem(new ValidationError("barcode", EXISTS)));
    }

    @Test
    public void whenValid_ThenNoValidationErrorsSentToObserver() {
        addItemToTransaction(item.getBarcode(), createdTransaction().getId(), transactionRepo, itemRepo, observer);

        assertTrue(observer.spyValidationErrors().isEmpty());
    }

    @Test
    public void whenValid_TransactionSentBackToObserverWithItem() {
        addItemToTransaction(item.getBarcode(), createdTransaction().getId(), transactionRepo, itemRepo, observer);

        assertThat(observer.spyCreatedTransaction().getItems(), hasItem(item));
    }

    private Transaction createdTransaction() {
        return createTransactionObserver.spyCreatedTransaction();
    }

    private TransactionRepository transactionRepo = new FakeTransactionRepository();
    private ItemRepository itemRepo = new FakeItemRepository();
    private AddItemToTransactionObserverSpy observer = new AddItemToTransactionObserverSpy();
    private CreateItemObserverSpy createItemObserver = new CreateItemObserverSpy();
    private CreateTransactionObserverSpy createTransactionObserver = new CreateTransactionObserverSpy();

    private String validBarcode = "valid barcode";
    private String validDisplayName = "valid display name";
    private int validPriceInCents = 1;

    private Item item = new Item(validDisplayName, validBarcode, validPriceInCents);
}