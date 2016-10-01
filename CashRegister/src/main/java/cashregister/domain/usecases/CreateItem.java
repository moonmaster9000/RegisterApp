package cashregister.domain.usecases;

import cashregister.domain.Constraint;
import cashregister.domain.values.ValidationError;

import java.util.ArrayList;

import static cashregister.domain.Constraint.POSITIVE;
import static cashregister.domain.Constraint.REQUIRED;

public class CreateItem {
    public static void createItem(String displayName, String barcode, int priceInCents, CreateItemObserver observer) {
        ArrayList<ValidationError> errors = new ArrayList<>();

        errors.add(new ValidationError("displayName", REQUIRED));
        errors.add(new ValidationError("barcode", REQUIRED));
        errors.add(new ValidationError("priceInCents", POSITIVE));

        observer.validationFailed(errors);
    }
}
