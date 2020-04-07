package com.javaguru.shoppinglist;

import com.javaguru.shoppinglist.console.ConsoleUI;
import com.javaguru.shoppinglist.repository.ProductInMemoryRepository;
import com.javaguru.shoppinglist.service.ProductService;
import com.javaguru.shoppinglist.service.validation.*;

import java.util.HashSet;
import java.util.Set;

class ShoppingListApplication {

    public static void main(String[] args) {
        ProductInMemoryRepository repository = new ProductInMemoryRepository();
        ProductValidationRule productDiscountValidationRule = new ProductDiscountValidationRule();
        ProductValidationRule productNameValidationRule = new ProductNameValidationRule();
        ProductValidationRule producPriceValidationRule = new ProductPriceValidationRule();
        ProductValidationRule productIsUniqueValidationRule = new ProductIsUniqueValidationRule(repository);
        Set<ProductValidationRule> validationRules = new HashSet<>();
        validationRules.add(productDiscountValidationRule);
        validationRules.add(productNameValidationRule);
        validationRules.add(producPriceValidationRule);
        validationRules.add(productIsUniqueValidationRule);
        ProductValidationService productValidationService = new ProductValidationService(validationRules);
        ProductService productService = new ProductService(repository, productValidationService);
        ConsoleUI consoleUI = new ConsoleUI(productService);
        consoleUI.execute();
    }
}