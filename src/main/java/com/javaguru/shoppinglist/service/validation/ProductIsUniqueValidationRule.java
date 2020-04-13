package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductRepository;

import org.springframework.stereotype.Component;

@Component
public class ProductIsUniqueValidationRule implements ProductValidationRule{

    private final ProductRepository repository;

    public ProductIsUniqueValidationRule(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public void validate (Product product){
        checkNotNull(product);
        if (!repository.isUnique(product)){
            throw new IllegalArgumentException("Product name is not unique!");
        }
    }

}