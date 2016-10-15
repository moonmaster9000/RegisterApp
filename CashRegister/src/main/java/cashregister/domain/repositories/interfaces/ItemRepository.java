package cashregister.domain.repositories.interfaces;

import cashregister.domain.entities.PersistenceControlledItemAttributes;

import java.util.List;

public interface ItemRepository {
    int count();

    void save(PersistenceControlledItemAttributes item);

    List<PersistenceControlledItemAttributes> getAll();
}
