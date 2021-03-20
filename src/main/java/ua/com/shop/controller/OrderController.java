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

    private ProductService productService;
    private OrderService orderService;

    public OrderController(ProductService productService, OrderService orderService) {
        this.productService = productService;
        this.orderService = orderService;
    }

    @GetMapping
    public String getAllOrders(Model model) {
        List<Order> orders = orderService.getAll();

        model.addAttribute("orders", orders);
        return "/orders";
    }

    @GetMapping("/additionalInfo")
    public String getOrdersAdditionalInfo(Model model) {
        List<OrderInfo> orderInfos = orderService.getOrdersInformation();

        model.addAttribute("orderInfos", orderInfos);
        return "/orderInfo";
    }

    @GetMapping("/createOrder")
    public String createOrderPage(Model model) {
        List<Product> products = productService.getAll();
        CreateOrderDto createOrderDto = new CreateOrderDto();

        products.forEach(product -> createOrderDto.getProductIdAndQuantity().put((int) product.getId(), 0));

        model.addAttribute("createOrderDto", createOrderDto);
        model.addAttribute("map", createOrderDto.getProductIdAndQuantity());
        model.addAttribute("products", products);
        return "/createOrder";
    }

    @PostMapping("/createOrder")
    public String createOrder(@ModelAttribute("createOrderDto") CreateOrderDto dto) {
        orderService.addOrder(dto.getProductIdAndQuantity());
        System.out.println(dto.getProductIdAndQuantity());
        return "redirect:/orders";
    }
}
