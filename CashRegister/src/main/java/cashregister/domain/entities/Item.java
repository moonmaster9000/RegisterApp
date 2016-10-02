package cashregister.domain.entities;

import cashregister.domain.values.ValidationError;

import java.util.ArrayList;
import java.util.List;

import static cashregister.domain.Constraint.POSITIVE;
import static cashregister.domain.Constraint.REQUIRED;

public class Item {
    private String displayName;
    private String barcode;
    private int priceInCents;
    private Object id;

    public Item(String displayName, String barcode, int priceInCents) {
        this.displayName = displayName;
        this.barcode = barcode;
        this.priceInCents = priceInCents;
    }

    public Item() { }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public boolean isValid() {
        return getValidationErrors().isEmpty();
    }

    public List<ValidationError> getValidationErrors() {
        ArrayList<ValidationError> errors = new ArrayList<>();

        if (displayName == null)
            errors.add(new ValidationError("displayName", REQUIRED));

        if (barcode == null)
            errors.add(new ValidationError("barcode", REQUIRED));

        if (priceInCents <= 0)
            errors.add(new ValidationError("priceInCents", POSITIVE));

        return errors;
    }
}
