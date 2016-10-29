package cashregister.domain.repositories.interfaces;

import cashregister.domain.entities.Item;

public interface ItemRepository extends EntityRepository<Item>{
    Item findByBarcode(String barcode);
}
