package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;

public interface ProductValidationRule {

    void validate(Product product) throws ProductValidationException, IllegalArgumentException;

    default void checkNotNull(Product product) throws ProductValidationException{
        if (product == null) { throw new ProductValidationException("Product must be not null"); }
    }

}