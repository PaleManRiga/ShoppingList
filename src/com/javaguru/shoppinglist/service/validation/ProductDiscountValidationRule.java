package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;

import java.math.BigDecimal;

public class ProductDiscountValidationRule implements ProductValidationRule {

    @Override
    public void validate (Product product) throws ProductValidationException, IllegalArgumentException{
        checkNotNull(product);
        final BigDecimal maxValue = BigDecimal.valueOf(100);
        if (product.getDiscount().compareTo(maxValue) == 1){
            throw new IllegalArgumentException("Discount should not be more than " + maxValue + "!");
        }
    }
}