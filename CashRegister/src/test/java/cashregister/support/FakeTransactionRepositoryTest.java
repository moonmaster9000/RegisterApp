package cashregister.support;

import cashregister.domain.entities.Transaction;
import cashregister.domain.repositories.interfaces.TransactionRepository;
import cashregister.tests.repositories.contracts.TransactionRepositoryContract;

public class FakeTransactionRepositoryTest extends TransactionRepositoryContract {
    @Override
    protected TransactionRepository createRepo() {
        return new FakeTransactionRepository();
    }

    @Override
    protected Transaction createEntity() {
        return new Transaction();
    }
}