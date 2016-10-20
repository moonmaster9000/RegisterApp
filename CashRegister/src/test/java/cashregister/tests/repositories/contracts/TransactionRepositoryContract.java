package cashregister.tests.repositories.contracts;

import cashregister.domain.entities.Item;
import cashregister.domain.entities.Transaction;
import cashregister.domain.repositories.interfaces.ItemRepository;
import cashregister.domain.repositories.interfaces.TransactionRepository;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public abstract class TransactionRepositoryContract extends EntityRepositoryContract<Transaction, TransactionRepository> {
    public abstract ItemRepository createItemRepo();

    @Test
    public void itSavesItemRelationships() {
        ItemRepository itemRepo = createItemRepo();
        Transaction t = new Transaction();

        Item i = new Item();
        itemRepo.save(i);

        t.addItem(i);
        repo.save(t);

        assertThat(repo.findById(t.getId()).getItems(), hasItem(i));
    }
}