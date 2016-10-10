package cashregister.domain.entities;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Item extends Entity {

    @NotNull private String displayName;
    @NotNull private String barcode;
    @Min(1)  private int priceInCents;

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

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public void setPriceInCents(int priceInCents) {
        this.priceInCents = priceInCents;
    }
}
