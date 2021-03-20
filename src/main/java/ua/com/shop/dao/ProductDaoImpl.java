package ua.com.shop.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.shop.exception.ProductNotFoundException;
import ua.com.shop.model.OrderItem;
import ua.com.shop.model.Product;
import ua.com.shop.model.ProductInfo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductDaoImpl.class);
    @PersistenceContext
    private EntityManager em;

    @Override
    public void addProduct(Product product) {
        em.persist(product);
        LOGGER.info("Product created, id = {}", product.getId());
    }

    @Override
    public Product getProductById(int id) {
        Product product = em.find(Product.class, (long) id);
        if (product == null) {
            throw new ProductNotFoundException("Product not found");
        }
        return product;
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p", Product.class);

        try {
            products = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public List<ProductInfo> getAllProductsWithTotalOrderedQuantity() {
        List<ProductInfo> products;
        TypedQuery<ProductInfo> query = em.createQuery(
                "select new ua.com.shop.model.ProductInfo (pr, sum(oi.quantity)) from OrderItem oi " +
                        "inner join Product pr " +
                        "on oi.product.id = pr.id " +
                        "group by oi.product.id, pr.id " +
                        "order by sum(oi.quantity) desc", ProductInfo.class
        );

        products = query.getResultList();
        return products;
    }

    @Override
    public void delete(int id) {
        Product product = em.find(Product.class, (long) id);
        TypedQuery<OrderItem> query = em.createQuery(
                "select oi from OrderItem oi " +
                        "where oi.product.id = " + id, OrderItem.class
        );

        List<OrderItem> orderItems = query.getResultList();
        orderItems.forEach(o -> em.remove(o.getOrder()));
        em.remove(product);
    }

    @Override
    public void deleteAll() {
        List<Product> products = getAll();
        products.forEach(product -> delete((int) product.getId()));
    }
}

