package cashregister.domain.entities;

import cashregister.domain.entities.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

class Item extends Entity implements UserControlledItemAttributes, PersistenceControlledItemAttributes {
    @NotBlank private String displayName;
    @NotNull  private String barcode;
    @Min(1)   private int priceInCents;

    private String id;

    public Item(String displayName, String barcode, int priceInCents) {
        this.displayName = displayName;
        this.barcode = barcode;
        this.priceInCents = priceInCents;
    }

    public Item() { }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String getBarcode() {
        return barcode;
    }

    @Override
    public int getPriceInCents() {
        return priceInCents;
    }

    @Override
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    @Override
    public void setPriceInCents(int priceInCents) {
        this.priceInCents = priceInCents;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }
}