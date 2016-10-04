package cashregister.domain.entities;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;

public class Item extends Entity {

    @NotBlank private String displayName;
    @NotBlank private String barcode;
    @Min(1)   private int priceInCents;

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
