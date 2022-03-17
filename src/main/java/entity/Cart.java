package entity;

public class Cart {
    private String productId;
    private String orderId;
    private int quantity;
    private int total;

    public Cart(String orderId, String productId, int quantity) {
        this.productId = productId;
        this.orderId = orderId;
        this.quantity = quantity;
    }

    public Cart() {

    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "productId='" + productId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", quantity=" + quantity +
                '}';
    }


}
