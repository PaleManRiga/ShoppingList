package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ProductDiscountValidationRuleTest {
    private final BigDecimal MAX_VALUE = new BigDecimal("100");
    private final BigDecimal MIN_PRICE_VALUE = new BigDecimal("20");

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    private ProductDiscountValidationRule victim = new ProductDiscountValidationRule();
    private Product input;

    @Test
    public void shouldThrowIllegalArgumentExceptionForMinValue(){
        input = product(BigDecimal.valueOf(100), BigDecimal.valueOf(-0.01));
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Discount must not be less than 0!");
        victim.validate(input);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionForMaxValue(){
        input = product(BigDecimal.valueOf(100), BigDecimal.valueOf(100.01));
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Discount should not be more than " + MAX_VALUE + "!");
        victim.validate(input);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionForLowPrice(){
        input = product(BigDecimal.valueOf(19.99), BigDecimal.valueOf(10));
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("You can not make a discount at a price of less than " + MIN_PRICE_VALUE + "!");
        victim.validate(input);
    }

    @Test
    public void shouldNotThrowException(){
        input = product(BigDecimal.valueOf(100), BigDecimal.valueOf(50));
        victim.validate(input);
    }

    private Product product(BigDecimal price, BigDecimal discount){
        Product product = new Product();
        product.setPrice(price);
        product.setDiscount(discount);
        return product;
    }

}