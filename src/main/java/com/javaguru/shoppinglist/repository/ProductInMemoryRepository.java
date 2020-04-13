package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.Product;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
@Profile("inmemorydb")
public class ProductInMemoryRepository implements ProductRepository{
    private Long productIdSequence = 0L;
    private Map<Long, Product> productRepository = new HashMap<>();

    public Product insert(Product product) {
        product.setId(productIdSequence);
        productRepository.put(productIdSequence, product);
        productIdSequence++;
        return product;
    }

    public Product findProductById(Long id) {
        return productRepository.get(id);
    }

    public void deleteProductById(Long id) {
        productRepository.remove(id);
    }

    public boolean isUnique(Product product){
        for(Product productInRepository : productRepository.values()){
            if (productInRepository.getName().equals(product.getName())){return false;}
        }
        return true;
    }
}

