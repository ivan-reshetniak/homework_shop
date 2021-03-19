package ua.com.shop.service;

import ua.com.shop.dao.ProductDao;
import ua.com.shop.dao.ProductDaoImpl;
import ua.com.shop.model.Product;
import ua.com.shop.model.ProductInfo;
import ua.com.shop.model.ProductStatus;

import java.time.LocalDateTime;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    private ProductDao productDao = new ProductDaoImpl();

    @Override
    public void addProduct(String name, int price, ProductStatus status) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setStatus(status);
        product.setCreatedAt(LocalDateTime.now());
        productDao.addProduct(product);
    }

    @Override
    public Product getProductById(int productId) {
        return productDao.getProductById(productId);
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
    public void deleteProduct(int id) {
        productDao.delete(id);
    }

    @Override
    public void deleteAll() {
        productDao.deleteAll();
    }

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }
}
