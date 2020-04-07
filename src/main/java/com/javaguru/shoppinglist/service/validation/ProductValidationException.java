package com.javaguru.shoppinglist.service.validation;

public class ProductValidationException extends RuntimeException {

    public ProductValidationException() {
    }

    public ProductValidationException(String message) {
        super(message);
    }
}