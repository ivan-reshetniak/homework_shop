package ua.com.shop.model;

import java.time.LocalDateTime;

public class OrderInfo {

    private long orderId;
    private int totalSum;
    private String productName;
    private int quantity;
    private LocalDateTime createdAt;

    public OrderInfo() {
    }

    public OrderInfo(long orderId, int totalSum, String productName, int quantity, LocalDateTime createdAt) {
        this.orderId = orderId;
        this.totalSum = totalSum;
        this.productName = productName;
        this.quantity = quantity;
        this.createdAt = createdAt;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public int getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(int totalSum) {
        this.totalSum = totalSum;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "OrderInfo{" +
                "orderId=" + orderId +
                ", totalSum=" + totalSum +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                ", createdAt=" + createdAt +
                '}';
    }
}
