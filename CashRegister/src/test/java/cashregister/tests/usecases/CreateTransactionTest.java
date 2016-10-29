package cashregister.tests.usecases;

import cashregister.domain.repositories.interfaces.TransactionRepository;
import cashregister.support.CreateTransactionObserverSpy;
import cashregister.support.FakeTransactionRepository;
import org.junit.Test;

import static cashregister.domain.usecases.CreateTransaction.createTransaction;
import static org.junit.Assert.*;

public class CreateTransactionTest {

    private CreateTransactionObserverSpy observer = new CreateTransactionObserverSpy();
    private TransactionRepository transactionRepo = new FakeTransactionRepository();

    @Test
    public void itReturnsATransactionWithAnIdToTheObserver() {
        createTransaction(observer, transactionRepo);

        assertNotNull(observer.spyCreatedTransaction().getId());
    }
}