package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.domain.ShoppingCart;
import com.javaguru.shoppinglist.domain.ShoppingCartProduct;
import com.javaguru.shoppinglist.repository.ShoppingCartProductRepository;
import com.javaguru.shoppinglist.repository.ShoppingCartRepository;

import org.springframework.stereotype.Component;

@Component
public class ShoppingCartProductService {

    private final ShoppingCartService shoppingCartService;
    private final ProductService productService;
    private final ShoppingCartProductRepository repository;

    public ShoppingCartProductService(ShoppingCartService shoppingCartService,
                                      ProductService productService,
                                      ShoppingCartProductRepository repository) {
        this.shoppingCartService = shoppingCartService;
        this.productService = productService;
        this.repository = repository;
    }

    public void assignProductToShoppingCart(Long productId, Long shoppingCartId, Long productsAmount) {
        Product product = productService.findProduct(productId);
        ShoppingCart shoppingCart = shoppingCartService.findShoppingCart(shoppingCartId);
        ShoppingCartProduct shoppingCartProduct = new ShoppingCartProduct(productsAmount, shoppingCart, product);
        repository.insert(shoppingCartProduct);
    }
}