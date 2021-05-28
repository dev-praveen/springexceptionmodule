package com.spring.api;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String msg){
        super(msg);
    }

    public ProductNotFoundException(String msg, Throwable throwable){
        super(msg, throwable);
    }
}
