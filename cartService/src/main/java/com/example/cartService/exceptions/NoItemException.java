package com.example.cartService.exceptions;

public class NoItemException extends RuntimeException {
    public NoItemException(String message) {
        super(message);
    }
} 