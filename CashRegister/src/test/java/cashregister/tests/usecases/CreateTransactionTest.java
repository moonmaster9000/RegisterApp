package cashregister.tests.usecases;

import cashregister.domain.entities.Transaction;
import cashregister.domain.repositories.interfaces.TransactionRepository;
import cashregister.support.FakeTransactionRepository;
import org.junit.Test;

import static cashregister.domain.usecases.CreateTransaction.createTransaction;
import static org.junit.Assert.assertNotNull;

public class CreateTransactionTest {

    private TransactionRepository transactionRepo = new FakeTransactionRepository();

    @Test
    public void itReturnsATransactionWithAnIdToTheObserver() {
        Transaction transaction = createTransaction(transactionRepo);

        assertNotNull(transaction.getId());
    }
}