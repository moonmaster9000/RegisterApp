package hibernateBackedRepos.tests;

import cashregister.domain.entities.Item;
import cashregister.domain.repositories.interfaces.ItemRepository;
import cashregister.tests.repositories.contracts.ItemRepositoryContract;
import hibernateBackedRepos.repositories.HibernateItemRepo;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateItemRepoTest extends ItemRepositoryContract {
    protected ItemRepository createRepo() {
        SessionFactory sessionFactory;
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        return new HibernateItemRepo(sessionFactory.openSession());
    }

    protected Item createEntity() {
        return new Item();
    }
}
