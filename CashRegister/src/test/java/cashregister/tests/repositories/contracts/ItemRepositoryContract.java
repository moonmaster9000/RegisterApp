package cashregister.tests.repositories.contracts;

import cashregister.domain.entities.Item;
import cashregister.domain.repositories.interfaces.ItemRepository;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public abstract class ItemRepositoryContract extends EntityRepositoryContract<Item, ItemRepository> {

    @Test
    public void findByBarcode() {
        Item item1 = createEntity();
        Item item2 = createEntity();

        item1.setBarcode("item1 barcode");
        item2.setBarcode("item2 barcode");

        repo.save(item1);
        repo.save(item2);

        assertEquals(item1, repo.findByBarcode(item1.getBarcode()));
        assertEquals(item2, repo.findByBarcode(item2.getBarcode()));

        assertNull(repo.findByBarcode("barcode does not exist"));
    }

}