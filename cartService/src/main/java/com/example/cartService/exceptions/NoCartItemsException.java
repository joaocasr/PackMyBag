package com.example.cartService.exceptions;

public class NoCartItemsException extends RuntimeException {
    /*
     * This instead should show a page that says that not item's exist in
     * the cart, showing a button that redirects the user to the main page.
     */
    public NoCartItemsException(String message) {
        super(message);
    }
} 