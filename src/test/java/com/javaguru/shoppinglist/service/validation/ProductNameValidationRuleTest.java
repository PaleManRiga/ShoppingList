package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ProductNameValidationRuleTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    private ProductNameValidationRule victim = new ProductNameValidationRule();
    private Product input;

    @Test
    public void shouldThrowIllegalArgumentExceptionForMinValue(){
        input = product("qw");
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Name length is incorrect!");
        victim.validate(input);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionForMaxValue(){
        input = product("123456789012345678901234567890123");
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Name length is incorrect!");
        victim.validate(input);
    }

    @Test
    public void shouldNotThrowException(){
        input = product("TEST");
        victim.validate(input);
    }

    private Product product(String name){
        Product product = new Product();
        product.setName(name);
        return product;
    }

}