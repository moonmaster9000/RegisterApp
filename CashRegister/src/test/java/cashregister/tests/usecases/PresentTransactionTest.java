package cashregister.tests.usecases;

import cashregister.domain.Constraint;
import cashregister.domain.entities.Transaction;
import cashregister.domain.repositories.interfaces.TransactionRepository;
import cashregister.domain.values.ValidationError;
import cashregister.support.CreateTransactionObserverSpy;
import cashregister.support.FakeTransactionRepository;
import cashregister.support.PresentTransactionObserverSpy;
import org.junit.Before;
import org.junit.Test;

import static cashregister.domain.usecases.CreateTransaction.createTransaction;
import static cashregister.domain.usecases.PresentTransaction.presentTransaction;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public class PresentTransactionTest {

    private PresentTransactionObserverSpy observer = new PresentTransactionObserverSpy();
    private TransactionRepository repo = new FakeTransactionRepository();
    private CreateTransactionObserverSpy createTransactionObserverSpy = new CreateTransactionObserverSpy();
    private Transaction transaction;

    @Before
    public void before() {
        createTransaction(createTransactionObserverSpy, repo);
        transaction = createTransactionObserverSpy.spyCreatedTransaction();
    }

    @Test
    public void invalidId() {
        presentTransaction("unknown Transaction ID", observer, repo);

        assertThat(
            observer.spyValidationErrors(),
            hasItem(new ValidationError("transactionId", Constraint.EXISTS))
        );
    }

    @Test
    public void validId() {
        presentTransaction(transaction.getId(), observer, repo);

        assertNull(observer.spyValidationErrors());
        assertEquals(transaction, observer.spyPresentedTransaction());
    }
}