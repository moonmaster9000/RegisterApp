package cashregister.tests.usecases;

import cashregister.domain.entities.Item;
import cashregister.domain.repositories.interfaces.ItemRepository;
import cashregister.domain.values.ValidationError;
import cashregister.support.CreateItemObserverSpy;
import cashregister.support.FakeItemRepository;
import org.junit.Before;
import org.junit.Test;

import static cashregister.domain.Constraint.POSITIVE;
import static cashregister.domain.Constraint.REQUIRED;
import static cashregister.domain.usecases.CreateItem.createItem;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.*;

public class CreateItemTest {
    @Test
    public void nullDisplayName() {
        item.setDisplayName(null);

        createItem(item, observer, itemRepo);

        assertThat(
            observer.spyValidationErrors(),
            hasItem(new ValidationError("displayName", REQUIRED))
        );
    }

    @Test
    public void emptyDisplayName() {
        item.setDisplayName("");

        createItem(item, observer, itemRepo);

        assertThat(
            observer.spyValidationErrors(),
            hasItem(new ValidationError("displayName", REQUIRED))
        );
    }

    @Test
    public void blankDisplayName() {
        item.setDisplayName("    ");

        createItem(item, observer, itemRepo);

        assertThat(
            observer.spyValidationErrors(),
            hasItem(new ValidationError("displayName", REQUIRED))
        );
    }

    @Test
    public void negativePrice() {
        item.setPriceInCents(-1);

        createItem(item, observer, itemRepo);

        assertThat(
            observer.spyValidationErrors(),
            hasItem(new ValidationError("priceInCents", POSITIVE))
        );
    }

    @Test
    public void emptyBarcode() {
        item.setBarcode(null);

        createItem(item, observer, itemRepo);

        assertThat(
            observer.spyValidationErrors(),
            hasItem(new ValidationError("barcode", REQUIRED))
        );
    }

    @Test
    public void validAttributes_noValidationErrors() {
        createItem(item, observer, itemRepo);

        assertNull(observer.spyValidationErrors());
    }

    @Test
    public void validAttributes_ItemIsCreated() {
        createItem(item, observer, itemRepo);

        assertEquals(1, itemRepo.count());
    }

    @Test
    public void validAttributes_SendsIdOfCreatedItemToObserver() {
        createItem(item, observer, itemRepo);

        assertNotNull(observer.spyCreatedItemId());
    }

    private String validDisplayName = "valid display name";
    private String validBarcode = "valid barcode";
    private int validPriceInCents = 1;
    private CreateItemObserverSpy observer = new CreateItemObserverSpy();
    private ItemRepository itemRepo = new FakeItemRepository();
    private Item item = new Item();

    @Before
    public void before() {
        item.setDisplayName(validDisplayName);
        item.setBarcode(validBarcode);
        item.setPriceInCents(validPriceInCents);
    }
}

