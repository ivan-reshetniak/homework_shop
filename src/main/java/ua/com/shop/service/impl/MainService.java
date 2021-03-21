package ua.com.shop.service.impl;

import ua.com.shop.model.ProductStatus;
import ua.com.shop.service.OrderService;
import ua.com.shop.service.ProductService;

import java.util.HashMap;
import java.util.Map;

public class MainService {

    private ProductService productService;
    private OrderService orderService;

    public MainService(ProductService productService, OrderService orderService) {
        this.productService = productService;
        this.orderService = orderService;
    }

    public void addTestData() {
        productService.addProduct("ps5", 500, ProductStatus.IN_STOCK);
        productService.addProduct("xbox", 550, ProductStatus.OUT_OF_STOCK);
        productService.addProduct("oneplus", 700, ProductStatus.RUNNING_IN_LOW);

        Map<Integer, Integer> productAndQuantity = new HashMap<>();
        productAndQuantity.put(1, 5);
        productAndQuantity.put(2, 4);

        Map<Integer, Integer> productAndQuantity1 = new HashMap<>();
        productAndQuantity1.put(1, 2);
        productAndQuantity1.put(3, 8);
        productAndQuantity1.put(2, 4);

        orderService.addOrder(productAndQuantity);
        orderService.addOrder(productAndQuantity1);
    }
}
