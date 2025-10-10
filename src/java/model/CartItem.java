package model;

public class CartItem {

    private int userId;
    private int productId;
    private int colorId;
    private int quantity;

    public CartItem(int productId, int colorId, int quantity) {
        this.productId = productId;
        this.colorId = colorId;
        this.quantity = quantity;
    }

    public CartItem(int userId, int productId, int colorId, int quantity) {
        this.userId = userId;
        this.productId = productId;
        this.colorId = colorId;
        this.quantity = quantity;
    }
    // getter setter...

    public CartItem() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
