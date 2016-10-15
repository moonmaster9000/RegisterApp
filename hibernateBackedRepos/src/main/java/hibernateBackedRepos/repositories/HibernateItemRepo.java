package hibernateBackedRepos.repositories;

import cashregister.domain.entities.PersistenceControlledItemAttributes;
import cashregister.domain.repositories.interfaces.ItemRepository;
import org.hibernate.Session;

import java.util.List;

public class HibernateItemRepo implements ItemRepository {
    private Session session;

    public HibernateItemRepo(Session session) {
        this.session = session;
    }

    public int count() {
        return ((Long)session.createQuery("select count(*) from PersistenceControlledItemAttributes").uniqueResult()).intValue();
    }

    public void save(PersistenceControlledItemAttributes item) {
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
    }

    public List<PersistenceControlledItemAttributes> getAll() {
        return (List<PersistenceControlledItemAttributes>)session.createQuery("from Item").list();
    }
}
