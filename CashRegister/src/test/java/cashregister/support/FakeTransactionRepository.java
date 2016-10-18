package cashregister.support;

import cashregister.domain.entities.Transaction;
import cashregister.domain.repositories.interfaces.TransactionRepository;

public class FakeTransactionRepository extends FakeEntityRepository<Transaction> implements TransactionRepository {
}
