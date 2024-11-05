package com.example.cartService.exceptions;

public class NoPaymentException extends RuntimeException {
    public NoPaymentException(String message) {
        super(message);
    }
} 