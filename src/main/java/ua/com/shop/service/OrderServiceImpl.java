package ua.com.shop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.shop.dao.OrderDao;
import ua.com.shop.dao.OrderDaoImpl;
import ua.com.shop.exception.OrderNotFoundException;
import ua.com.shop.model.Order;
import ua.com.shop.model.OrderInfo;
import ua.com.shop.model.OrderItems;
import ua.com.shop.model.OrderStatus;
import ua.com.shop.model.Product;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);
    private OrderDao orderDao = new OrderDaoImpl();
    private ProductServiceImpl productService = new ProductServiceImpl();

    @Override
    public Order getOrderById(int orderId) {
        return orderDao.getOrderById(orderId);
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }

    @Override
    public void addOrder(Map<Integer, Integer> productAndQuantity) {
        Order order = new Order();
        order.setStatus(OrderStatus.IN_PROCESS);
        order.setCreatedAt(LocalDateTime.now());
        order.setUserId(new Random().nextInt(7));

        Set<Integer> productIds = productAndQuantity.keySet();
        productIds.forEach(id -> {
            try {
                Product product = productService.getProductById(id);

                OrderItems orderItems = new OrderItems();
                orderItems.setOrder(order);
                orderItems.setProduct(product);
                orderItems.setQuantity(productAndQuantity.get(id));

                order.getOrderItems().add(orderItems);
            } catch (OrderNotFoundException e) {
                LOGGER.info("Product with id = {} does not exist", id);
            }
        });
        orderDao.addOrder(order);
        LOGGER.info("Order created {}", order);
    }

    @Override
    public List<OrderInfo> getOrdersInformation() {
        return orderDao.getOrdersInformation();
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void setProductService(ProductServiceImpl productService) {
        this.productService = productService;
    }
}
