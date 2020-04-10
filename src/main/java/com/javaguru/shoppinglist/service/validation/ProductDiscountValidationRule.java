package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductDiscountValidationRule implements ProductValidationRule {

    private final BigDecimal MAX_VALUE = new BigDecimal("100");
    private final BigDecimal MIN_PRICE_VALUE = new BigDecimal("20");

    @Override
    public void validate (Product product){
        checkNotNull(product);
        if (product.getDiscount().compareTo(MAX_VALUE) == 1){
            throw new IllegalArgumentException("Discount should not be more than " + MAX_VALUE + "!");
        }

        if (product.getDiscount().compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("Discount must not be less than 0!");
        }

        if ((product.getDiscount().compareTo(BigDecimal.ZERO) > 0) && (product.getPrice().compareTo(MIN_PRICE_VALUE) < 0)){
            throw new IllegalArgumentException("You can not make a discount at a price of less than " + MIN_PRICE_VALUE + "!");
        }
    }
}