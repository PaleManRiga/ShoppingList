package com.javaguru.shoppinglist;

import java.math.BigDecimal;

public class ProductValidationService {

    public void validate(Product product){
        if(product.getName().length() < 3 || product.getName().length() > 32){
            System.out.println("Product name must be from 3 to 32 char long ");
            throw new ProductValidationException("Product name must be from 3 to 32 char long ");
        }
        if (product.getPrice().compareTo(BigDecimal.ZERO) <=0){
            System.out.println("Price must be more than 0");
            throw new ProductValidationException("Price must be more than 0");
        }
        if (product.getDiscount().compareTo(BigDecimal.valueOf(100)) > 0){
            System.out.println("Discount can't be more tha 100%");
            throw new ProductValidationException("Discount can't be more than 100%");
        }
    }
}
