package cashregister.tests.usecases;

import cashregister.domain.entities.Transaction;
import cashregister.domain.repositories.interfaces.TransactionRepository;
import cashregister.domain.values.ValidationError;
import cashregister.support.FakeTransactionRepository;
import org.junit.Before;
import org.junit.Test;

import static cashregister.domain.Constraint.EXISTS;
import static cashregister.domain.usecases.CreateTransaction.createTransaction;
import static cashregister.domain.usecases.PresentTransaction.presentTransaction;
import static cashregister.tests.usecases.UseCaseAssertions.assertThrowsValidationError;
import static org.junit.Assert.assertEquals;

public class PresentTransactionTest {

    private TransactionRepository repo = new FakeTransactionRepository();
    private Transaction transaction;

    @Before
    public void before() {
        transaction = createTransaction(repo);
    }

    @Test
    public void invalidId() {
        assertThrowsValidationError(
            () -> presentTransaction("unknown Transaction ID", repo),
            new ValidationError("transactionId", EXISTS)
        );
    }

    @Test
    public void validId() {
        Transaction presentedTransaction = presentTransaction(transaction.getId(), repo);
        assertEquals(transaction, presentedTransaction);
    }
}