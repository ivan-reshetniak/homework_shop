package ua.com.shop.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.shop.dto.CreateOrderDto;
import ua.com.shop.model.Order;
import ua.com.shop.model.OrderInfo;
import ua.com.shop.model.Product;
import ua.com.shop.service.OrderService;
import ua.com.shop.service.ProductService;

import java.util.List;

@RequestMapping("/orders")
public class OrderController {

    private static final String ORDERS = "orders";
    private static final String ORDER_INFOS = "orderInfos";
    private static final String CREATE_ORDER_DTO = "createOrderDto";
    private static final String MAP = "map";
    private static final String PRODUCTS = "products";
    private ProductService productService;
    private OrderService orderService;

    @GetMapping
    public String getAllOrders(Model model) {
        final List<Order> orders = orderService.getAll();

        model.addAttribute(ORDERS, orders);
        return "/orders";
    }

    @GetMapping("/additionalInfo")
    public String getOrdersAdditionalInfo(Model model) {
        final List<OrderInfo> orderInfos = orderService.getOrdersInformation();

        model.addAttribute(ORDER_INFOS, orderInfos);
        return "/orderInfo";
    }

    @GetMapping("/createOrder")
    public String createOrderPage(Model model) {
        final List<Product> products = productService.getAll();
        final CreateOrderDto createOrderDto = new CreateOrderDto();

        products.forEach(product -> createOrderDto.getProductIdAndQuantity().put((int) product.getId(), 0));

        model.addAttribute(CREATE_ORDER_DTO, createOrderDto);
        model.addAttribute(MAP, createOrderDto.getProductIdAndQuantity());
        model.addAttribute(PRODUCTS, products);
        return "/createOrder";
    }

    @PostMapping("/createOrder")
    public String createOrder(@ModelAttribute("createOrderDto") CreateOrderDto dto) {
        orderService.addOrder(dto.getProductIdAndQuantity());
        System.out.println(dto.getProductIdAndQuantity());
        return "redirect:/orders";
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
}
