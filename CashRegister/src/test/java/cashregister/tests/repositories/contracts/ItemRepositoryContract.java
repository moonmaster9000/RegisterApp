package cashregister.tests.repositories.contracts;

import cashregister.domain.entities.Item;
import cashregister.domain.repositories.interfaces.ItemRepository;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.*;

public abstract class ItemRepositoryContract {
    protected ItemRepository repo;

    protected abstract void createRepo();

    @Before
    public void before() {
        createRepo();
    }

    @Test
    public void itCreatesUniqueIds() {
        Item item1 = new Item();
        Item item2 = new Item();

        assertNull(item1.getId());
        assertNull(item2.getId());

        repo.save(item1);
        repo.save(item2);

        assertNotNull(item1.getId());
        assertNotNull(item2.getId());
        assertNotEquals(item1.getId(), item2.getId());
    }

    @Test
    public void itSavesTheItemForLaterRetrieval() {
        Item item = new Item();
        repo.save(item);
        assertThat(repo.getAll(), hasItem(item));
    }

    @Test
    public void itCounts() {
        Item item = new Item();
        repo.save(item);
        assertEquals(1, repo.count());
    }
}