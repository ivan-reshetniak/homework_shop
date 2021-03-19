package ua.com.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.shop.formatter.OrderInfoFormatter;
import ua.com.shop.formatter.ProductFormatter;
import ua.com.shop.formatter.ProductInfoFormatter;
import ua.com.shop.model.ProductStatus;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

@Component
public class MainService {

    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;
    private Scanner scanner = new Scanner(System.in);
    private String passwordFromProperties = getPasswordFromProperties();

    public void mainMenu() {
        System.out.println("Enter 1 to create product\n" +
                "Enter 2 to create order\n" +
                "Enter 3 to show products\n" +
                "Enter 4 to show additional products information\n" +
                "Enter 5 to show orders information\n" +
                "Enter 6 to delete product by id\n" +
                "Enter 7 to delete all products\n" +
                "Enter 0 to exit (call System.exit())\n");
        int input;
        do {
            input = Integer.parseInt(scanner.nextLine());
        } while (input > 7 || input < 0);

        switch (input) {
            case 1:
                createProduct();
                break;
            case 2:
                createOrder();
                break;
            case 3:
                showProducts();
                break;
            case 4:
                showProductInfo();
                break;
            case 5:
                showOrdersInfo();
                break;
            case 6:
                deleteProductById();
                break;
            case 7:
                deleteAllProducts();
                break;
            case 0:
                System.exit(1);
        }
    }

    public void createProduct() {
        System.out.println("Enter product name: ");
        String name = scanner.nextLine();
        System.out.println("Enter product price: ");
        int price = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter product status (0, 1, 2): ");
        ProductStatus status = ProductStatus.get(Integer.parseInt(scanner.nextLine()));

        productService.addProduct(name, price, status);
    }

    public void createOrder() {
        Map<Integer, Integer> productAndQuantity = new HashMap<>();
        int productId;
        int productQuantity;
        int input;

        do {
            System.out.println("Enter product id: ");
            productId = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter product quantity: ");
            productQuantity = Integer.parseInt(scanner.nextLine());
            productAndQuantity.put(productId, productQuantity);

            System.out.println("Enter 1 to add more products to order");
            System.out.println("Enter 0 to finish");
            input = Integer.parseInt(scanner.nextLine());
        } while (input != 0);
        orderService.addOrder(productAndQuantity);
    }

    public void showProducts() {
        System.out.println(ProductFormatter.format(productService.getAll()));
        System.out.println("Press enter to return to main menu");
        scanner.nextLine();
    }

    public void showProductInfo() {
        System.out.println(ProductInfoFormatter.format(productService.getAllProductsWithTotalOrderedQuantity()));
        System.out.println("Press enter to return to main menu");
        scanner.nextLine();
    }

    public void showOrdersInfo() {
        System.out.println(OrderInfoFormatter.format(orderService.getOrdersInformation()));
        System.out.println("Press enter to return to main menu");
        scanner.nextLine();
    }

    public void deleteProductById() {
        System.out.println("Enter product id" +
                " (this operation will delete all orders associated with this product): ");
        int productId = Integer.parseInt(scanner.nextLine());

        productService.deleteProduct(productId);
    }

    public void deleteAllProducts() {
        System.out.println("This operation will delete all products and all orders" +
                "which associated with them");
        System.out.println("Enter password (from local.properties) : ");
        String password = scanner.nextLine();

        if (password.equals(passwordFromProperties)) {
            productService.deleteAll();
        } else {
            System.out.println("Incorrect password");
        }
    }

    private String getPasswordFromProperties() {
        Properties properties = new Properties();
        try {
            properties.load(MainService.class.getClassLoader().getResourceAsStream("local.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty("password");
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

    public void setProductService(ProductServiceImpl productService) {
        this.productService = productService;
    }

    public void setOrderService(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }
}
