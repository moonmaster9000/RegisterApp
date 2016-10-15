package cashregister.domain.entities;

public interface UserControlledItemAttributes extends PresentableItemAttributes {
    public void setDisplayName(String displayName);
    public void setBarcode(String barcode);
    public void setPriceInCents(int priceInCents);
}
