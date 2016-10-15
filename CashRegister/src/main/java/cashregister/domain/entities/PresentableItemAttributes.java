package cashregister.domain.entities;

public interface PresentableItemAttributes {
    String getDisplayName();
    String getBarcode();
    String getId();
    int getPriceInCents();
}
