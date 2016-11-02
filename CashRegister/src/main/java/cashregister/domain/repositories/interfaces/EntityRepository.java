package cashregister.domain.repositories.interfaces;

import cashregister.domain.entities.Entity;

import java.util.List;

public interface EntityRepository<T extends Entity> {
    void save(T entity);

    List<T> getAll();

    int count();

    T findById(String id);

    void deleteAll();
}
