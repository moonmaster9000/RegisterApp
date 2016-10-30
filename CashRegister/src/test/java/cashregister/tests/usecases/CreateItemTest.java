package cashregister.tests.usecases;

import cashregister.domain.entities.Item;
import cashregister.domain.repositories.interfaces.ItemRepository;
import cashregister.domain.values.ValidationError;
import cashregister.support.FakeItemRepository;
import org.junit.Before;
import org.junit.Test;

import static cashregister.domain.Constraint.POSITIVE;
import static cashregister.domain.Constraint.REQUIRED;
import static cashregister.domain.usecases.CreateItem.createItem;
import static cashregister.tests.usecases.UseCaseAssertions.assertThrowsValidationError;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CreateItemTest {
    @Test
    public void nullDisplayName() {
        item.setDisplayName(null);

        assertThrowsValidationError(
            () -> createItem(item, itemRepo),
            new ValidationError("displayName", REQUIRED)
        );
    }

    @Test
    public void emptyDisplayName() {
        item.setDisplayName("");

        assertThrowsValidationError(
            () -> createItem(item, itemRepo),
            new ValidationError("displayName", REQUIRED)
        );
    }

    @Test
    public void blankDisplayName() {
        item.setDisplayName("    ");

        assertThrowsValidationError(
            () -> createItem(item, itemRepo),
            new ValidationError("displayName", REQUIRED)
        );
    }

    @Test
    public void negativePrice() {
        item.setPriceInCents(-1);

        assertThrowsValidationError(
            () -> createItem(item, itemRepo),
            new ValidationError("priceInCents", POSITIVE)
        );
    }

    @Test
    public void emptyBarcode() {
        item.setBarcode(null);

        assertThrowsValidationError(
            () -> createItem(item, itemRepo),
            new ValidationError("barcode", REQUIRED)
        );
    }

    @Test
    public void validAttributes_ItemIsCreated() {
        createItem(item, itemRepo);

        assertEquals(1, itemRepo.count());
    }

    @Test
    public void validAttributes_SendsIdOfCreatedItemToObserver() {
        Item createdItem = createItem(item, itemRepo);

        assertNotNull(createdItem.getId());
    }

    private String validDisplayName = "valid display name";
    private String validBarcode = "valid barcode";
    private int validPriceInCents = 1;
    private ItemRepository itemRepo = new FakeItemRepository();
    private Item item = new Item();

    @Before
    public void before() {
        item.setDisplayName(validDisplayName);
        item.setBarcode(validBarcode);
        item.setPriceInCents(validPriceInCents);
    }
}