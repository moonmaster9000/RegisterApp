package cashregister.support;

import cashregister.domain.entities.Item;
import cashregister.domain.repositories.interfaces.ItemRepository;

import java.util.ArrayList;

public class FakeItemRepository extends FakeEntityRepository<Item>  implements ItemRepository {
    public FakeItemRepository(){
        entities = new ArrayList<>();
    }

    @Override
    public Item findByBarcode(String barcode) {
        return where(e -> e.getBarcode().equals(barcode));
    }
}
