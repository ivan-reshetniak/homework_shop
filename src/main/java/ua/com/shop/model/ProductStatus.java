package ua.com.shop.model;

public enum ProductStatus {
    OUT_OF_STOCK(0), IN_STOCK(1), RUNNING_IN_LOW(2);

    private final int id;

    ProductStatus(int id) {
        this.id = id;
    }

    public int getValue() {
        return this.id;
    }

    public static ProductStatus get(int i) {
        return ProductStatus.values()[i];
    }
}
