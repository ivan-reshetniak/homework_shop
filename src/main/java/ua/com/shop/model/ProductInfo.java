package ua.com.shop.model;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProductInfo extends Product {

    private Product product;
    private long totalQuantity;
}
