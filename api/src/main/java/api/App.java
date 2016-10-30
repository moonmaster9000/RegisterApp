package api;

import cashregister.domain.repositories.interfaces.ItemRepository;
import cashregister.domain.repositories.interfaces.TransactionRepository;
import hibernateBackedRepos.repositories.HibernateItemRepo;
import hibernateBackedRepos.repositories.HibernateTransactionRepo;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public ItemRepository itemRepository(){
        return new HibernateItemRepo(sessionFactory().openSession());
    }

    @Bean
    public TransactionRepository transactionRepository(){
        return new HibernateTransactionRepo(sessionFactory().openSession());
    }

    @Bean
    public SessionFactory sessionFactory() {
        SessionFactory sessionFactory;
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        return sessionFactory;
    }
}
