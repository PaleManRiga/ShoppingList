package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;

public class ProductNameValidationRule implements ProductValidationRule{

    @Override
    public void validate (Product product) throws ProductValidationException, IllegalArgumentException{
        checkNotNull(product);
        if (product.getName().length() < 3 || product.getName().length() > 32){
            throw new IllegalArgumentException("Name length is incorrect!");
        }
    }

}