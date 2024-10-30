package com.example.cartService.exceptions;

public class NoCartException extends RuntimeException {
    public NoCartException(String message) {
        super(message);
    }
} 