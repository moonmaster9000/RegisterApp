package cashregister.tests.usecases;

import cashregister.domain.entities.EntityFactory;
import cashregister.domain.entities.UserControlledItemAttributes;
import cashregister.domain.repositories.interfaces.ItemRepository;
import cashregister.domain.values.ValidationError;
import cashregister.support.CreateItemObserverSpy;
import cashregister.support.FakeItemRepository;
import org.junit.Before;
import org.junit.Test;

import static cashregister.domain.Constraint.*;
import static cashregister.domain.entities.CreateItem.*;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.*;

public class CreateItemTest {
    @Test
    public void nullDisplayName() {
        attrs.setDisplayName(null);

        createItem(attrs, observer, itemRepo);

        assertThat(
            observer.spyValidationErrors(),
            hasItem(new ValidationError("displayName", REQUIRED))
        );
    }

    @Test
    public void emptyDisplayName() {
        attrs.setDisplayName("");

        createItem(attrs, observer, itemRepo);

        assertThat(
            observer.spyValidationErrors(),
            hasItem(new ValidationError("displayName", REQUIRED))
        );
    }

    @Test
    public void blankDisplayName() {
        attrs.setDisplayName("   ");

        createItem(attrs, observer, itemRepo);

        assertThat(
            observer.spyValidationErrors(),
            hasItem(new ValidationError("displayName", REQUIRED))
        );
    }

    @Test
    public void negativePrice() {
        attrs.setPriceInCents(-1);

        createItem(attrs, observer, itemRepo);

        assertThat(
            observer.spyValidationErrors(),
            hasItem(new ValidationError("priceInCents", POSITIVE))
        );
    }


    @Test
    public void emptyBarcode() {
        attrs.setBarcode(null);

        createItem(attrs, observer, itemRepo);

        assertThat(
            observer.spyValidationErrors(),
            hasItem(new ValidationError("barcode", REQUIRED))
        );
    }

    @Test
    public void validAttributes_noValidationErrors() {
        createItem(attrs, observer, itemRepo);

        assertNull(observer.spyValidationErrors());
    }

    @Test
    public void validAttributes_ItemIsCreated() {
        createItem(attrs, observer, itemRepo);

        assertEquals(1, itemRepo.count());
    }

    @Test
    public void validAttributes_SendsIdOfCreatedItemToObserver() {
        createItem(attrs, observer, itemRepo);

        assertNotNull(observer.spyCreatedItemId());
    }

    private String validDisplayName = "valid display name";
    private String validBarcode = "valid barcode";
    private int validPriceInCents = 1;
    private CreateItemObserverSpy observer = new CreateItemObserverSpy();
    private ItemRepository itemRepo = new FakeItemRepository();

    private UserControlledItemAttributes attrs = new EntityFactory().createUserControlledItemAttributes();

    @Before
    public void before() {
        attrs.setDisplayName(validDisplayName);
        attrs.setBarcode(validBarcode);
        attrs.setPriceInCents(validPriceInCents);
    }
}

