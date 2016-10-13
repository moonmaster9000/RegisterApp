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
    @Test
    public void nullDisplayName() {
        String nullDisplayName = null;

        createItem(nullDisplayName, validBarcode, validPriceInCents, observer, itemRepo);

        assertThat(
            observer.spyValidationErrors(),
            hasItem(new ValidationError("displayName", REQUIRED))
        );
    }

    @Test
    public void emptyDisplayName() {
        String emptyDisplayName = "";

        createItem(emptyDisplayName, validBarcode, validPriceInCents, observer, itemRepo);

        assertThat(
            observer.spyValidationErrors(),
            hasItem(new ValidationError("displayName", REQUIRED))
        );
    }

    @Test
    public void blankDisplayName() {
        String emptyDisplayName = "   ";

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
    private CreateItemObserverSpy observer = new CreateItemObserverSpy();
    private ItemRepository itemRepo = new FakeItemRepository();
}

