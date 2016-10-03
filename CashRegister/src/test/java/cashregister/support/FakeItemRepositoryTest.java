package cashregister.support;

import cashregister.tests.repositories.contracts.ItemRepositoryContract;

public class FakeItemRepositoryTest extends ItemRepositoryContract {
    @Override
    protected void createRepo() {
        repo = new FakeItemRepository();
    }
}
