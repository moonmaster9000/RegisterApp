package cashregister.support;

import cashregister.domain.entities.Item;
import cashregister.tests.repositories.contracts.ItemRepositoryContract;

public class FakeItemRepositoryTest extends ItemRepositoryContract {
    @Override
    protected FakeItemRepository createRepo() {
        return new FakeItemRepository();
    }

    @Override
    protected Item createEntity() {
        return new Item();
    }
}
