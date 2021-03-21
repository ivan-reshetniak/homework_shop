package ua.com.shop.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "order_items")
public class OrderItem implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @Id
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private int quantity;

    public OrderItem() {
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderItems{" +
                "product=" + product.getName() +
                ", quantity=" + quantity +
                '}';
    }
}
