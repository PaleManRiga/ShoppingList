package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductInMemoryRepository;
import com.javaguru.shoppinglist.service.validation.ProductValidationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.math.BigDecimal;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {
    @Mock
    private ProductInMemoryRepository repository;
    @Mock
    private ProductValidationService validationService;
    @InjectMocks
    private ProductService victim;
    @Captor
    private ArgumentCaptor<Product> productCaptor;

    @Test
    public void shouldCreateProductSuccessfully(){
        Product product = product();
        when(repository.insert(product)).thenReturn(product);
        Long result = victim.createProduct(product);
        verify(validationService).validate(productCaptor.capture());
        Product productCaptorResult = productCaptor.getValue();
        assertEquals(product(), productCaptorResult);
        assertEquals(product.getId(), result);
    }

    @Test
    public void shouldFindProduct(){
        when(repository.findProductById(10L)).thenReturn(product());
        Product result = repository.findProductById(10L);
        assertEquals(product(), result);
    }

    private Product product(){
        Product product = new Product();
        product.setName("Test name");
        product.setCategory(Category.valueOf("FISH"));
        product.setDescription("testing");
        product.setDiscount(BigDecimal.valueOf(50));
        product.setPrice(BigDecimal.valueOf(1000));
        product.setId(10L);
        return product;
    }
}