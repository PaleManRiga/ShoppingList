package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductInMemoryRepository;
import com.javaguru.shoppinglist.service.Category;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductIsUniqueValidationRuleTest {
    @Mock
    ProductInMemoryRepository repository;

    @InjectMocks
    private ProductIsUniqueValidationRule victim;

    private Product product = product();

    @Test
    public void validate() {
        when(repository.isUnique(product)).thenReturn(false);

        assertThatThrownBy(() -> victim.validate(product())).isInstanceOf(IllegalArgumentException.class).hasMessage("Product name is not unique!");
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