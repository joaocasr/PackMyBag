package com.example.cartService.dto;

import org.springframework.http.HttpStatus;

public class ErrorResponse {
    private HttpStatus status;
    private String message;

    public ErrorResponse(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    // Getters and setters
    public HttpStatus getStatus() { return status; }
    public String getMessage() { return message; }
} 