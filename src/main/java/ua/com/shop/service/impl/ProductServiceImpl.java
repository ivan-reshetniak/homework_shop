package ua.com.shop.service.impl;

import org.springframework.transaction.annotation.Transactional;
import ua.com.shop.dao.ProductDao;
import ua.com.shop.exception.ProductNotFoundException;
import ua.com.shop.model.Product;
import ua.com.shop.model.ProductInfo;
import ua.com.shop.model.ProductStatus;
import ua.com.shop.service.ProductService;

import java.time.LocalDateTime;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    private ProductDao productDao;

    @Override
    @Transactional
    public void addProduct(String name, int price, ProductStatus status) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setStatus(status);
        product.setCreatedAt(LocalDateTime.now());
        productDao.addProduct(product);
    }

    @Override
    public Product getProductById(long productId) {
        return productDao.getProductById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
    }

    @Override
    public List<Product> getAll() {
        return productDao.getAll();
    }

    @Override
    public List<ProductInfo> getAllProductsWithTotalOrderedQuantity() {
        return productDao.getAllProductsWithTotalOrderedQuantity();
    }

    @Override
    @Transactional
    public void deleteProduct(int id) {
        productDao.delete(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        productDao.deleteAll();
    }

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }
}
