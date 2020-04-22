package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.Product;

public interface ProductRepository {
    public Product insert(Product product);

    public Product findProductById(Long id);

    public void deleteProductById(Long id);

    public boolean isUnique(Product product);
}
