package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductInMemoryRepository;
import com.javaguru.shoppinglist.service.validation.ProductValidationService;
import org.springframework.stereotype.Component;


@Component
public class ProductService {
    private final ProductInMemoryRepository repository;
    private final ProductValidationService validationService;

    public ProductService(ProductInMemoryRepository repository, ProductValidationService validationService) {
        this.repository = repository;
        this.validationService = validationService;
    }

    public Long createProduct(Product product) {
        validationService.validate(product);
        final Product createdProduct = repository.insert(product);
        return createdProduct.getId();
    }

    public Product findProductById(Long id) {
        return repository.findProductById(id);
    }

    public void deleteProduct(Long id) {
        repository.deleteProductById(id);
    }
}