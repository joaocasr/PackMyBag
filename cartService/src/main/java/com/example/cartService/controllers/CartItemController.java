package com.example.cartService.controllers;

import com.example.cartService.dto.*;
import com.example.cartService.services.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartItemController {
    
    private final CartService cartService;

    @Autowired
    public CartItemController(CartService cartService){this.cartService=cartService;}

    @GetMapping("/{username}")
    public List<CartItemDTO> getCartItems(@PathVariable String username) {
        return cartService.getCartItems(username);
    }

    @PostMapping("/addItem")
    public ResponseEntity<?> addItemToCart(@RequestBody CartItemInsertDTO itemBody) {
        try {
            cartService.addToCart(itemBody);
            return ResponseEntity.ok().body("Item added to cart successfully!");
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/removeItem")
    public ResponseEntity<?> removeItemFromCart(@RequestBody CartItemRemoveDTO itemBody) {
        try {
            cartService.removeFromCart(itemBody);
            return ResponseEntity.ok().body("Item removed from cart successfully!");
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/clearCart")
    public ResponseEntity<?> clearCart(@RequestBody String username) {
        try {
            cartService.clearCart(username);
            return ResponseEntity.ok().body("Cart cleared successfully!");
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/changeQuantity")
    public ResponseEntity<?> changeItemQuantity(@RequestBody CartItemChangeQuantityDTO itemBody) {
        try {
            cartService.changeItemQuantity(itemBody);
            return ResponseEntity.ok().body("Item quantity updated successfully!");
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/count/{username}")
    public int getCartItemsCount(@PathVariable String username) {
        return cartService.getCartItemsCount(username);
    }

    @PostMapping("/createPayment")
    public ResponseEntity<?> createPayment(@RequestBody CartPaymentDTO paymentInfo) {
        try {
            cartService.createPayment(paymentInfo);
            return ResponseEntity.ok().body("Payment created successfully!");
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/changePaymentStatus")
    public ResponseEntity<?> changePaymentStatus(@RequestBody CartPaymentDTO paymentInfo) {
        try {
            cartService.changePaymentStatus(paymentInfo);
            return ResponseEntity.ok().body("Payment status updated successfully!");
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
