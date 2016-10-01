package cashregister.tests.usecases;

import cashregister.domain.usecases.CreateItem;
import cashregister.domain.usecases.CreateItemObserver;
import cashregister.domain.values.ValidationError;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static cashregister.domain.Constraint.*;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.*;

public class CreateItemTest {
    private CreateItemObserverSpy observer = new CreateItemObserverSpy();

    @Test
    public void noDisplayName() {
        String emptyDisplayName = null;

        CreateItem.createItem(emptyDisplayName, validBarcode, validPriceInCents, observer);

        assertThat(
            observer.spyValidationErrors(),
            hasItem(new ValidationError("displayName", REQUIRED))
        );
    }

    private String validBarcode = "valid barcode";
    private int validPriceInCents = 1;
}

class CreateItemObserverSpy implements CreateItemObserver {
    private ArrayList<ValidationError> spyValidationErrors;

    public List<ValidationError> spyValidationErrors() {
        return spyValidationErrors;
    }

    @Override
    public void validationFailed(ArrayList<ValidationError> errors) {
        spyValidationErrors = errors;
    }
}