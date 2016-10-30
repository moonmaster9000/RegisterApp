package api.requests;

public class AddItemRequest {
    private String barcode;
    private String transactionId;

    public AddItemRequest(String barcode, String transactionId) {
        this.barcode = barcode;
        this.transactionId = transactionId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
}
