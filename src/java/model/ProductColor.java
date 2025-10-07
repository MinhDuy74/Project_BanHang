package model;

public class ProductColor {
    private int colorId;
    private int productId;
    private String colorName;
    private String colorCode;

    public ProductColor() {}

    public ProductColor(int colorId, int productId, String colorName, String colorCode) {
        this.colorId = colorId;
        this.productId = productId;
        this.colorName = colorName;
        this.colorCode = colorCode;
    }

    public int getColorId() { return colorId; }
    public void setColorId(int colorId) { this.colorId = colorId; }

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public String getColorName() { return colorName; }
    public void setColorName(String colorName) { this.colorName = colorName; }

    public String getColorCode() { return colorCode; }
    public void setColorCode(String colorCode) { this.colorCode = colorCode; }
}