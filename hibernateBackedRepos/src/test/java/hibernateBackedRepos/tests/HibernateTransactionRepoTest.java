package hibernateBackedRepos.tests;

import cashregister.domain.entities.Transaction;
import cashregister.domain.repositories.interfaces.ItemRepository;
import cashregister.domain.repositories.interfaces.TransactionRepository;
import cashregister.tests.repositories.contracts.TransactionRepositoryContract;
import hibernateBackedRepos.repositories.HibernateItemRepo;
import hibernateBackedRepos.repositories.HibernateTransactionRepo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateTransactionRepoTest extends TransactionRepositoryContract {
    private Session session;

    protected TransactionRepository createRepo() {
        return new HibernateTransactionRepo(getSession());
    }

    protected Transaction createEntity() {
        return new Transaction();
    }

    @Override
    public ItemRepository createItemRepo() {
        return new HibernateItemRepo(getSession());
    }

    private Session getSession() {
        if (session == null){
            final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
            SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            session = sessionFactory.openSession();
        }

        return session;
    }
}
