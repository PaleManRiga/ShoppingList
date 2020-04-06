package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;

import java.math.BigDecimal;

public class ProductPriceValidationRule implements ProductValidationRule{

    @Override
    public void validate (Product product) throws ProductValidationException, IllegalArgumentException{
        checkNotNull(product);
        if (product.getPrice().compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("Price must be greater than 0!");
        }
    }
}