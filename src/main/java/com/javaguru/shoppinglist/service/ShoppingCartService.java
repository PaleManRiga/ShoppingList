package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.domain.ShoppingCart;
import com.javaguru.shoppinglist.repository.ShoppingCartRepository;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartService {

    private final ShoppingCartRepository repository;

    public ShoppingCartService(ShoppingCartRepository repository) {
        this.repository = repository;
    }

    public Long createShoppingCart(ShoppingCart shoppingCart){
        ShoppingCart createdShoppingCart = repository.insert(shoppingCart);
        return createdShoppingCart.getId();
    }

    public ShoppingCart findShoppingCart(Long id){
        return repository.findProductById(id);
    }
}