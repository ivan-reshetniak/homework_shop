package ua.com.shop.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.com.shop.dao.impl.OrderDaoImpl;
import ua.com.shop.exception.OrderEmptyException;
import ua.com.shop.model.Order;
import ua.com.shop.model.OrderItem;
import ua.com.shop.model.OrderStatus;
import ua.com.shop.model.Product;
import ua.com.shop.model.ProductStatus;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    private static final Long ORDER_ID = 1L;
    private static final OrderStatus ORDER_STATUS = OrderStatus.IN_PROCESS;
    private static final LocalDateTime ORDER_DATE_CREATED = LocalDateTime.parse("2021-03-21T00:00");
    private static final Integer USER_ID = 5;
    private static final String PRODUCT_NAME = "name";
    private static final Long PRODUCT_ID = 1L;
    private static final ProductStatus PRODUCT_STATUS = ProductStatus.RUNNING_IN_LOW;
    private static final LocalDateTime PRODUCT_CREATED_DATE = LocalDateTime.parse("2021-03-21T00:00");
    private static final Integer PRODUCT_PRICE = 700;
    private static final Integer PRODUCT_QUANTITY = 5;

    @Captor
    private ArgumentCaptor<Order> captor;
    @Mock
    private ProductServiceImpl productService;
    @Mock
    private OrderDaoImpl orderDao;
    @InjectMocks
    private OrderServiceImpl orderService;

    private Order createOrder() {
        Order order = new Order();
        order.setId(ORDER_ID);
        order.setStatus(ORDER_STATUS);
        order.setCreatedAt(ORDER_DATE_CREATED);
        order.setUserId(USER_ID);
        order.setOrderItems(Collections.singleton(createOrderItem()));
        return order;
    }

    @Test
    void shouldReturnOrder_whenTryToGetOrderById() {
        when(orderDao.getOrderById(ORDER_ID.intValue())).thenReturn(createOrder());

        assertEquals(ORDER_ID, orderService.getOrderById(ORDER_ID.intValue()).getId());
    }

    @Test
    void shouldReturnOrderList_whenTryToGetAllOrders() {
        when(orderDao.getAll()).thenReturn(Collections.singletonList(createOrder()));

        assertEquals(1, orderService.getAll().size());
    }

    @Test
    void shouldThrowOrderEmptyException_whenTryToAddOrderWithoutOrderItems() {
        assertThrows(OrderEmptyException.class, () -> orderService.addOrder(Collections.emptyMap()));
    }

    @Test
    void shouldAddOrder_whenTryToAddValidOrder() {
        when(productService.getProductById(PRODUCT_ID)).thenReturn(createProduct());

        orderService.addOrder(createOrderMap());
        verify(orderDao, times(1)).addOrder(captor.capture());
        assertEquals(1, captor.getValue().getOrderItems().size());
    }

    private OrderItem createOrderItem() {
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(createProduct());
        return orderItem;
    }

    private Product createProduct() {
        Product product = new Product();
        product.setId(PRODUCT_ID);
        product.setName(PRODUCT_NAME);
        product.setStatus(PRODUCT_STATUS);
        product.setPrice(PRODUCT_PRICE);
        product.setCreatedAt(PRODUCT_CREATED_DATE);
        return product;
    }

    private Map<Integer, Integer> createOrderMap() {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(PRODUCT_ID.intValue(), PRODUCT_QUANTITY);
        return map;
    }
}