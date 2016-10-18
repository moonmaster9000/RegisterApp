package cashregister.support;

import cashregister.domain.entities.Entity;
import cashregister.domain.repositories.interfaces.EntityRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

public class FakeEntityRepository<T extends Entity> implements EntityRepository<T> {
    protected List<T> entities = new ArrayList<>();

    @Override
    public int count() {
        return entities.size();
    }

    @Override
    public T findById(String id) {
        return where(e -> e.getId().equals(id));
    }

    @Override
    public void save(T entity) {
        if (entity.getId() == null)
            entity.setId(UUID.randomUUID().toString());

        entities.add(entity);
    }

    @Override
    public List<T> getAll() {
        return entities;
    }

    protected T where(Predicate<T> itemPredicate) {
        return entities.stream().filter(itemPredicate).findFirst().orElse(null);
    }
}
