package ua.com.shop.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.shop.exception.OrderNotFoundException;
import ua.com.shop.model.Order;
import ua.com.shop.model.OrderInfo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderDaoImpl.class);
    @PersistenceContext
    private EntityManager em;

    @Override
    public void addOrder(Order order) {
        try {
            em.persist(order);
        } catch (Exception e) {
            LOGGER.error("Exception while creating order");
            throw new RuntimeException();
        }
    }

    @Override
    public Order getOrderById(int orderId) {
        Order order = em.find(Order.class, (long) orderId);
        if (order == null) {
            throw new OrderNotFoundException("Order not found");
        }
        return order;
    }

    @Override
    public List<Order> getAll() {
        TypedQuery<Order> query = em.createQuery("SELECT o FROM Order o", Order.class);
        List<Order> orders = query.getResultList();
        return orders;
    }

    @Override
    public List<OrderInfo> getOrdersInformation() {
        List<OrderInfo> orderInfos;
        TypedQuery<OrderInfo> query = em.createQuery(
                "select new ua.com.shop.model.OrderInfo (o.id, pr.price * oi.quantity, pr.name, oi.quantity, o.createdAt) " +
                        "from Order o " +
                        "inner join OrderItem oi " +
                        "on o.id = oi.order.id " +
                        "inner join Product pr " +
                        "on oi.product.id = pr.id " +
                        "group by o.id, pr.name, oi.quantity, pr.price " +
                        "order by o.id", OrderInfo.class
        );
        orderInfos = query.getResultList();
        return orderInfos;
    }
}
