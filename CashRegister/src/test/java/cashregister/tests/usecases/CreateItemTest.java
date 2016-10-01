package cashregister.tests.usecases;

import cashregister.domain.usecases.CreateItem;
import org.junit.Test;

import static org.junit.Assert.*;

public class CreateItemTest {
    @Test
    public void noDisplayName() {
        String emptyDisplayName = null;

        CreateItem.createItem(emptyDisplayName, validBarcode, validPriceInCents);
    }

    private String validBarcode = "valid barcode";
    private int validPriceInCents = 1;
}