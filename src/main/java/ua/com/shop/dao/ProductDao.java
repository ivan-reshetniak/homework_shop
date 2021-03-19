package ua.com.shop.dao;

import ua.com.shop.model.Product;
import ua.com.shop.model.ProductInfo;

import java.util.List;

public interface ProductDao {

    void addProduct(Product product);

    Product getProductById(int id);

    List<Product> getAll();

    List<ProductInfo> getAllProductsWithTotalOrderedQuantity();

    void delete(int id);

    void deleteAll();
}
