package ua.com.shop.service;

import ua.com.shop.model.Product;
import ua.com.shop.model.ProductInfo;
import ua.com.shop.model.ProductStatus;

import java.util.List;

public interface ProductService {

    void addProduct(String name, int price, ProductStatus status);

    Product getProductById(long productId);

    List<Product> getAll();

    List<ProductInfo> getAllProductsWithTotalOrderedQuantity();

    void deleteProduct(int id);

    void deleteAll();
}
