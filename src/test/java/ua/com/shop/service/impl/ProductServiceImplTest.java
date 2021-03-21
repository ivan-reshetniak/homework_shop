package ua.com.shop.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.com.shop.dao.impl.ProductDaoImpl;
import ua.com.shop.exception.ProductNotFoundException;
import ua.com.shop.model.Product;
import ua.com.shop.model.ProductStatus;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    private static final String PRODUCT_NAME = "name";
    private static final Long PRODUCT_ID = 1L;
    private static final ProductStatus PRODUCT_STATUS = ProductStatus.RUNNING_IN_LOW;
    private static final LocalDateTime PRODUCT_CREATED_DATE = LocalDateTime.parse("2021-03-21T00:00");
    private static final Integer PRODUCT_PRICE = 700;
    private static final Long NOT_EXISTING_PRODUCT_ID = 5L;

    @Captor
    private ArgumentCaptor<Product> captor;
    @Mock
    private ProductDaoImpl productDao;
    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void shouldReturnProductList_whenTryToGetAllProducts() {
        when(productDao.getAll()).thenReturn(Collections.singletonList(createProduct()));

        assertEquals(1, productService.getAll().size());
    }

    @Test
    void shouldReturnProduct_whenTryToGetProductById() {
        when(productDao.getProductById(PRODUCT_ID)).thenReturn(Optional.of(createProduct()));

        assertEquals(createProduct(), productService.getProductById(PRODUCT_ID));
    }

    @Test
    void shouldAddNewProduct_whenTryToSaveProduct() {
        productService.addProduct(PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_STATUS);

        verify(productDao, times(1)).addProduct(captor.capture());
        assertEquals(PRODUCT_NAME, captor.getValue().getName());
    }

    @Test
    void shouldThrowProductNotFoundException_whenTryToGetNotExistingProduct() {
        when(productDao.getProductById(NOT_EXISTING_PRODUCT_ID)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.getProductById(NOT_EXISTING_PRODUCT_ID));
    }

    private Product createProduct() {
        Product product = new Product();
        product.setId(PRODUCT_ID);
        product.setName(PRODUCT_NAME);
        product.setStatus(PRODUCT_STATUS);
        product.setPrice(PRODUCT_PRICE);
        product.setCreatedAt(PRODUCT_CREATED_DATE);
        return product;
    }
}