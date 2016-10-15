package cashregister.support;

import cashregister.domain.entities.PersistenceControlledItemAttributes;
import cashregister.domain.repositories.interfaces.ItemRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FakeItemRepository implements ItemRepository {
    private List<PersistenceControlledItemAttributes> items;

    public FakeItemRepository(){
        items = new ArrayList<>();
    }

    @Override
    public int count() {
        return items.size();
    }

    @Override
    public void save(PersistenceControlledItemAttributes item) {
        item.setId(UUID.randomUUID().toString());
        items.add(item);
    }

    @Override
    public List<PersistenceControlledItemAttributes> getAll() {
        return items;
    }


}
