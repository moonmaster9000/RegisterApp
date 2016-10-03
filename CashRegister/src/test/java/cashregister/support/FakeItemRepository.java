package cashregister.support;

import cashregister.domain.entities.Item;
import cashregister.domain.repositories.interfaces.ItemRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FakeItemRepository implements ItemRepository {
    private List<Item> items;

    public FakeItemRepository(){
        items = new ArrayList<>();
    }

    @Override
    public int count() {
        return items.size();
    }

    @Override
    public void save(Item item) {
        item.setId(UUID.randomUUID().toString());
        items.add(item);
    }

    @Override
    public List<Item> getAll() {
        return items;
    }


}
