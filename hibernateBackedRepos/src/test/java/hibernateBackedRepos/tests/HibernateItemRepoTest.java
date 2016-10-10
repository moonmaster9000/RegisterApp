package hibernateBackedRepos.tests;

import cashregister.tests.repositories.contracts.ItemRepositoryContract;
import hibernateBackedRepos.repositories.HibernateItemRepo;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateItemRepoTest extends ItemRepositoryContract {
    protected void createRepo() {
        SessionFactory sessionFactory;
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        repo = new HibernateItemRepo(sessionFactory.openSession());
    }
}
