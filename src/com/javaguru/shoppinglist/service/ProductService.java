package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductInMemoryRepository;
import com.javaguru.shoppinglist.service.validation.ProductValidationException;
import com.javaguru.shoppinglist.service.validation.ProductValidationService;

public class ProductService {

    private ProductInMemoryRepository repository = new ProductInMemoryRepository();
    private ProductValidationService validationService = new ProductValidationService();

    public Long createProduct(Product product) throws ProductValidationException, IllegalArgumentException{
        validationService.validate(product);
        Product createdProduct = repository.insert(product);
        return createdProduct.getId();
    }

    public Product findProduct(Long id){
        return repository.findProductById(id);
    }

    public void deletProduct(Long id) {
        repository.deletProductById(id);
    }
}