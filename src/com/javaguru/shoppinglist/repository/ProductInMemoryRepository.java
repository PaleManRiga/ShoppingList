package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.Product;

import java.util.HashMap;
import java.util.Map;

public class ProductInMemoryRepository {
    private Long PRODUCT_ID_SEQUENCE = 0L;
    private Map<Long, Product> productMap = new HashMap<>();

    public Product insert(Product product) {
        product.setId(PRODUCT_ID_SEQUENCE);
        productMap.put(PRODUCT_ID_SEQUENCE, product);
        PRODUCT_ID_SEQUENCE++;
        return product;
    }

    public Product findProductById(Long id) {
        return productMap.get(id);
    }
}