package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.Category;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.verify;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ProductValidationServiceTest {

    @Mock
    private ProductValidationRule productDiscountValidationRule;

    @Mock
    private ProductValidationRule productNameValidationRule;

    @Mock
    private ProductValidationRule productPriceValidationRule;

    @Mock
    private ProductValidationRule productIsUniqueValidationRule;

    @Captor
    private ArgumentCaptor<Product> captor;

    private ProductValidationService victim;

    private Product product = product();

    @Before
    public void setUp(){
        Set<ProductValidationRule> validationRules = new HashSet<>();
        validationRules.add(productDiscountValidationRule);
        validationRules.add(productNameValidationRule);
        validationRules.add(productPriceValidationRule);
        validationRules.add(productIsUniqueValidationRule);
        victim = new ProductValidationService(validationRules);
    }

    @Test
    public void shouldValidate(){
        victim.validate(product);
        verify(productDiscountValidationRule).validate(captor.capture());
        verify(productNameValidationRule).validate(captor.capture());
        verify(productPriceValidationRule).validate(captor.capture());
        verify(productIsUniqueValidationRule).validate(captor.capture());
        List<Product> resultList = captor.getAllValues();
        assertThat(resultList).containsOnly(product);
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