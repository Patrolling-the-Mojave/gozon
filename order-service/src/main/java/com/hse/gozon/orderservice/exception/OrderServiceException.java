package com.hse.gozon.orderservice.exception;


public class OrderServiceException extends RuntimeException{
    public OrderServiceException(String message, Throwable cause){
        super(message, cause);
    }
}
