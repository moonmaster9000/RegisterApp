package cashregister.tests.entities;

import cashregister.domain.entities.Item;
import cashregister.domain.entities.Transaction;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TransactionTest {

    @Test
    public void itSumsUpThePriceOfItems() {
        Transaction t = new Transaction();

        assertEquals(0, t.getTotalInCents());

        Item item = new Item();
        item.setPriceInCents(1);
        t.addItem(item);

        assertEquals(1, t.getTotalInCents());

        Item item2 = new Item();
        item2.setPriceInCents(1);
        t.addItem(item2);

        assertEquals(2, t.getTotalInCents());
    }

}