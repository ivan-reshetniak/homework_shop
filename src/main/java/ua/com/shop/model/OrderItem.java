package ua.com.shop.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "order_items")
@EqualsAndHashCode
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

    @Override
    public String toString() {
        return "OrderItems{" +
                "product=" + product.getName() +
                ", quantity=" + quantity +
                '}';
    }
}
