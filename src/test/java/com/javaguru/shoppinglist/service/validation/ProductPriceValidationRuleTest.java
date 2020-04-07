package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

public class ProductPriceValidationRuleTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    private ProductPriceValidationRule victim = new ProductPriceValidationRule();
    private Product input;

    @Test
    public void shouldThrowIllegalArgumentExceptionForMinValue(){
        input = product(BigDecimal.valueOf(0));
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Price must be greater than 0!");
        victim.validate(input);
    }

    @Test
    public void shouldNotThrowException(){
        input = product(BigDecimal.valueOf(0.01));
        victim.validate(input);
    }

    private Product product(BigDecimal price){
        Product product = new Product();
        product.setPrice(price);
        return product;
    }

}