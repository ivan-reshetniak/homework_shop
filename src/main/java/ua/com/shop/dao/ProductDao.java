package ua.com.shop.dao;

import ua.com.shop.model.Product;
import ua.com.shop.model.ProductInfo;

import java.util.List;
import java.util.Optional;

public interface ProductDao {

    void addProduct(Product product);

    Optional<Product> getProductById(long id);

    List<Product> getAll();

    List<ProductInfo> getAllProductsWithTotalOrderedQuantity();

    void delete(int id);

    void deleteAll();
}
