package cashregister.domain.entities;

import static cashregister.domain.Constraint.POSITIVE;
import static cashregister.domain.Constraint.REQUIRED;


public class Item extends Entity {
    private String displayName;
    private String barcode;
    private int priceInCents;

    static {
        validations.put("displayName", REQUIRED);
        validations.put("barcode", REQUIRED);
        validations.put("priceInCents", POSITIVE);
    }

    public Item(String displayName, String barcode, int priceInCents) {
        this.displayName = displayName;
        this.barcode = barcode;
        this.priceInCents = priceInCents;
    }

    public Item() { }

    public String getDisplayName() {
        return displayName;
    }

    public String getBarcode() {
        return barcode;
    }

    public int getPriceInCents() {
        return priceInCents;
    }
}
