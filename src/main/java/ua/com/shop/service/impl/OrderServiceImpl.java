package ua.com.shop.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import ua.com.shop.dao.OrderDao;
import ua.com.shop.exception.OrderEmptyException;
import ua.com.shop.model.Order;
import ua.com.shop.model.OrderInfo;
import ua.com.shop.model.OrderItem;
import ua.com.shop.model.OrderStatus;
import ua.com.shop.model.Product;
import ua.com.shop.service.OrderService;
import ua.com.shop.service.ProductService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);
    private OrderDao orderDao;
    private ProductService productService;

    @Override
    public Order getOrderById(int orderId) {
        return orderDao.getOrderById(orderId);
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }

    @Override
    @Transactional
    public void addOrder(Map<Integer, Integer> productAndQuantity) {
        Order order = new Order();
        order.setStatus(OrderStatus.IN_PROCESS);
        order.setCreatedAt(LocalDateTime.now());
        order.setUserId(new Random().nextInt(7));

        Set<Integer> productIds = productAndQuantity.keySet();
        productIds.forEach(id -> {
            if (productAndQuantity.get(id) != 0) {
                final Product product = productService.getProductById(id);
                order.getOrderItems().add(createOrderItem(product, order, productAndQuantity.get(id)));
            }
        });
        if (order.getOrderItems().isEmpty()) {
            throw new OrderEmptyException("Order can not be empty");
        }
        orderDao.addOrder(order);
        LOGGER.info("Order created {}", order);
    }

    @Override
    public List<OrderInfo> getOrdersInformation() {
        return orderDao.getOrdersInformation();
    }

    private OrderItem createOrderItem(Product product, Order order, int quantity) {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setProduct(product);
        orderItem.setQuantity(quantity);
        return orderItem;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}
