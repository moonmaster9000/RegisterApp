package hibernateBackedRepos.repositories;

import cashregister.domain.entities.Item;
import cashregister.domain.repositories.interfaces.ItemRepository;
import org.hibernate.Session;

import java.util.List;

public class HibernateItemRepo implements ItemRepository {
    private Session session;

    public HibernateItemRepo(Session session) {
        this.session = session;
    }

    public int count() {
        return ((Long)session.createQuery("select count(*) from Item").uniqueResult()).intValue();
    }

    public void save(Item item) {
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
    }

    public List<Item> getAll() {
        return (List<Item>)session.createQuery("from Item").list();
    }
}
