package cashregister.tests.repositories.contracts;

import cashregister.domain.entities.Entity;
import cashregister.domain.repositories.interfaces.EntityRepository;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.*;

public abstract class EntityRepositoryContract<EntityType extends Entity, RepositoryType extends EntityRepository<EntityType>> {
    protected RepositoryType repo;

    protected abstract RepositoryType createRepo();
    protected abstract EntityType createEntity();

    @Before
    public void before() {
        repo = createRepo();
    }

    @Test
    public void itCreatesUniqueIds() {
        EntityType entity1 = createEntity();
        EntityType entity2 = createEntity();

        assertNull(entity1.getId());
        assertNull(entity2.getId());

        repo.save(entity1);
        repo.save(entity2);

        assertNotNull(entity1.getId());
        assertNotNull(entity2.getId());
        assertNotEquals(entity1.getId(), entity2.getId());
    }

    @Test
    public void itHonorsProvidedIds() {
        EntityType entity = createEntity();
        String providedId = "providedId";
        entity.setId(providedId);

        repo.save(entity);

        assertEquals(providedId, entity.getId());
        assertEquals(entity, repo.findById(providedId));
    }

    @Test
    public void whenSavingExistingEntity_ItPreservesId() {
        EntityType entity = createEntity();

        repo.save(entity);
        String generatedId = entity.getId();
        repo.save(entity);

        assertEquals(generatedId, entity.getId());
    }

    @Test
    public void itSavesTheItemForLaterRetrieval() {
        EntityType entity = createEntity();
        repo.save(entity);
        assertThat(repo.getAll(), hasItem(entity));
    }

    @Test
    public void itFindsById() {
        EntityType entity1 = createEntity();
        EntityType entity2 = createEntity();

        repo.save(entity1);
        repo.save(entity2);

        assertEquals(entity1, repo.findById(entity1.getId()));
        assertEquals(entity2, repo.findById(entity2.getId()));
        assertEquals(null, repo.findById("unknown id"));
    }

    @Test
    public void itCounts() {
        EntityType entity = createEntity();
        repo.save(entity);
        assertEquals(1, repo.count());
    }

    @Test
    public void itDeletesAllEntities() {
        EntityType entity = createEntity();
        repo.save(entity);
        repo.deleteAll();
        assertEquals(0, repo.count());
    }
}
