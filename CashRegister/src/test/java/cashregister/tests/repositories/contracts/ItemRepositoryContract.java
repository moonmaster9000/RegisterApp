package cashregister.tests.repositories.contracts;

import cashregister.domain.entities.PersistenceControlledItemAttributes;
import cashregister.domain.repositories.interfaces.ItemRepository;
import org.junit.Before;
import org.junit.Test;

import static cashregister.domain.entities.EntityFactory.createPersistenceControlledItemAttributes;
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
        PersistenceControlledItemAttributes item1 = createPersistenceControlledItemAttributes();
        PersistenceControlledItemAttributes item2 = createPersistenceControlledItemAttributes();

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
        PersistenceControlledItemAttributes item = createPersistenceControlledItemAttributes();

        repo.save(item);
        assertThat(repo.getAll(), hasItem(item));
    }

    @Test
    public void itCounts() {
        PersistenceControlledItemAttributes item = createPersistenceControlledItemAttributes();

        repo.save(item);
        assertEquals(1, repo.count());
    }
}