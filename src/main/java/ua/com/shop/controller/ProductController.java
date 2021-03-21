package ua.com.shop.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.shop.dto.CreateProductDto;
import ua.com.shop.model.Product;
import ua.com.shop.model.ProductInfo;
import ua.com.shop.model.ProductStatus;
import ua.com.shop.service.ProductService;

import java.util.List;

@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String getAllProducts(Model model) {
        List<Product> products = productService.getAll();

        model.addAttribute("products", products);
        return "products";
    }

    @PostMapping
    public String deleteProduct(@RequestParam("id") int id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }

    @PostMapping("/deleteAll")
    public String deleteAllProducts() {
        productService.deleteAll();
        return "redirect:/products";
    }

    @GetMapping("/additionalInfo")
    public String getAdditionalInfo(Model model) {
        List<ProductInfo> productInfos = productService.getAllProductsWithTotalOrderedQuantity();

        model.addAttribute("productInfos", productInfos);
        return "/productInfos";
    }

    @GetMapping("/createProduct")
    public String getCreateProductPage(Model model) {
        CreateProductDto createProductDto = new CreateProductDto();

        model.addAttribute("createProductDto", createProductDto);
        return "/createProduct";
    }

    @PostMapping("/createProduct")
    public String createProduct(CreateProductDto dto) {
        productService.addProduct(dto.getName(), dto.getPrice(), ProductStatus.get(dto.getStatus()));
        return "redirect:/products";
    }
}
