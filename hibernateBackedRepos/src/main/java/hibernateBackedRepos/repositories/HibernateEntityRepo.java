package hibernateBackedRepos.repositories;

import cashregister.domain.entities.Entity;
import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

class HibernateEntityRepo<T extends Entity> {
    protected Session session;
    private String entityName;

    public HibernateEntityRepo(String entityName){
        this.entityName = entityName;
    }

    public int count() {
        return ((Long)session.createQuery("select count(*) from " + entityName).uniqueResult()).intValue();
    }

    public List<T> getAll() {
        return (List<T>)session.createQuery("from " + entityName).list();
    }

    public void save(T entity) {
        session.beginTransaction();

        if (entity.getId() == null){
            session.save(entity);
        } else {
            session.replicate(entity, ReplicationMode.EXCEPTION);
        }

        session.getTransaction().commit();
    }

    public void deleteAll(){
        session.beginTransaction();
        session.createQuery("delete from " + entityName).executeUpdate();
        session.getTransaction().commit();
    }

    public T findById(String id) {
        return findBy("id", id);
    }

    protected T findBy(String propertyName, String value) {
        Query query = session.createQuery("from " + entityName + " where "+propertyName+"=:"+propertyName);
        query.setParameter(propertyName, value);
        return (T)query.list().stream().findFirst().orElse(null);
    }
}
