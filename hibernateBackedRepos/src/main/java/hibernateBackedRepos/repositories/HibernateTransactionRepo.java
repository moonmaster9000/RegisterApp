package hibernateBackedRepos.repositories;

import cashregister.domain.entities.Transaction;
import cashregister.domain.repositories.interfaces.TransactionRepository;
import org.hibernate.Session;

public class HibernateTransactionRepo extends HibernateEntityRepo<Transaction> implements TransactionRepository {
    public HibernateTransactionRepo(Session session) {
        super("Transaction");
        this.session = session;
    }
}
