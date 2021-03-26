package ua.com.shop.model;

public class ProductInfo extends Product {

    private Product product;
    private long totalQuantity;

    public ProductInfo() {
    }

    public ProductInfo(Product product, long totalQuantity) {
        this.product = product;
        this.totalQuantity = totalQuantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public long getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(long totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    @Override
    public String toString() {
        return "ProductInfo{" +
                "product=" + product +
                ", totalQuantity=" + totalQuantity +
                '}';
    }
}
