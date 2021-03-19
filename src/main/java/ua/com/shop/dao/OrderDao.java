package ua.com.shop.dao;

import ua.com.shop.model.Order;
import ua.com.shop.model.OrderInfo;

import java.util.List;

public interface OrderDao {

    void addOrder(Order order);

    Order getOrderById(int orderId);

    List<Order> getAll();

    List<OrderInfo> getOrdersInformation();
}
