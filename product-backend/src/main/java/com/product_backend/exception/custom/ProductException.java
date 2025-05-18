package com.product_backend.exception.custom;

public class ProductException extends RuntimeException{

    public ProductException(String message){
        super(message);
    }

}