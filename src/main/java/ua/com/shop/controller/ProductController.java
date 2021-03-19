package ua.com.shop.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.shop.model.Product;
import ua.com.shop.service.ProductService;

import java.util.List;

@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    @GetMapping
    public String getAllProducts(Model model) {
        List<Product> products = productService.getAll();

        model.addAttribute("products", products);
        return "products";
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}
