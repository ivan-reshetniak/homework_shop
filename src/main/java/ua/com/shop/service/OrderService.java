package ua.com.shop.service;

import ua.com.shop.model.Order;
import ua.com.shop.model.OrderInfo;

import java.util.List;
import java.util.Map;

public interface OrderService {

    Order getOrderById(int orderId);

    List<Order> getAll();

    void addOrder(Map<Integer, Integer> productAndQuantity);

    List<OrderInfo> getOrdersInformation();
}
