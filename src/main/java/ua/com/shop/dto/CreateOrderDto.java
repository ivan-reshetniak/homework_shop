package ua.com.shop.dto;

import java.util.HashMap;
import java.util.Map;

public class CreateOrderDto {

    private Map<Integer, Integer> productIdAndQuantity = new HashMap<>();

    public Map<Integer, Integer> getProductIdAndQuantity() {
        return productIdAndQuantity;
    }

    public void setProductIdAndQuantity(Map<Integer, Integer> productIdAndQuantity) {
        this.productIdAndQuantity = productIdAndQuantity;
    }
}
