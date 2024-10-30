package com.example.cartService.exceptions;

public class NoClientException extends RuntimeException {
    public NoClientException(String message) {
        super(message);
    }
} 