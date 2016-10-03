package cashregister.tests.usecases;

import cashregister.domain.repositories.interfaces.ItemRepository;
import cashregister.domain.values.ValidationError;
import cashregister.support.CreateItemObserverSpy;
import cashregister.support.FakeItemRepository;
import org.junit.Test;

import static cashregister.domain.Constraint.*;
import static cashregister.domain.usecases.CreateItem.*;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.*;

public class CreateItemTest {
    private CreateItemObserverSpy observer = new CreateItemObserverSpy();
    private ItemRepository itemRepo = new FakeItemRepository();

    @Test
    public void noDisplayName() {
        String emptyDisplayName = null;

        createItem(emptyDisplayName, validBarcode, validPriceInCents, observer, itemRepo);

        assertThat(
            observer.spyValidationErrors(),
            hasItem(new ValidationError("displayName", REQUIRED))
        );
    }

    @Test
    public void negativePrice() {
        int negativePriceInCents = -1;

        createItem(validDisplayName, validBarcode, negativePriceInCents, observer, itemRepo);

        assertThat(
            observer.spyValidationErrors(),
            hasItem(new ValidationError("priceInCents", POSITIVE))
        );
    }

    @Test
    public void emptyBarcode() {
        String emptyBarcode = null;

        createItem(validDisplayName, emptyBarcode, validPriceInCents, observer, itemRepo);

        assertThat(
            observer.spyValidationErrors(),
            hasItem(new ValidationError("barcode", REQUIRED))
        );
    }

    @Test
    public void validAttributes_noValidationErrors() {
        createItem(validDisplayName, validBarcode, validPriceInCents, observer, itemRepo);

        assertNull(observer.spyValidationErrors());
    }

    @Test
    public void validAttributes_ItemIsCreated() {
        createItem(validDisplayName, validBarcode, validPriceInCents, observer, itemRepo);

        assertEquals(1, itemRepo.count());
    }

    @Test
    public void validAttributes_SendsIdOfCreatedItemToObserver() {
        createItem(validDisplayName, validBarcode, validPriceInCents, observer, itemRepo);

        assertNotNull(observer.spyCreatedItemId());
    }

    private String validDisplayName = "valid display name";
    private String validBarcode = "valid barcode";
    private int validPriceInCents = 1;
}

