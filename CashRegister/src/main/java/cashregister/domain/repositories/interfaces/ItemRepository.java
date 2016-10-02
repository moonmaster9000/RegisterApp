package cashregister.domain.repositories.interfaces;

import cashregister.domain.entities.Item;

import java.util.List;

public interface ItemRepository {
    int count();

    void save(Item item);

    List<Item> getAll();
}
