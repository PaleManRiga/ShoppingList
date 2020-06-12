package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductRepository;
import com.javaguru.shoppinglist.service.validation.ProductValidationService;

import org.springframework.stereotype.Component;


@Component
public class ProductService {
    private final ProductRepository repository;
    private final ProductValidationService validationService;

    public ProductService(ProductRepository repository, ProductValidationService validationService) {
        this.repository = repository;
        this.validationService = validationService;
    }

    public Long createProduct(Product product) {
        validationService.validate(product);
        final Product createdProduct = repository.insert(product);
        return createdProduct.getId();
    }

    public Product findProduct(Long id) {
        return repository.findProductById(id);
    }

    public void deleteProduct(Long id) {
        repository.deleteProductById(id);
    }
}