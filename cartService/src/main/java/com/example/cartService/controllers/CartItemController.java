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


@RestController
@RequestMapping("/api/cart")
public class CartItemController {
    
    private final CartService cartService;

    @Autowired
    public CartItemController(CartService cartService){this.cartService=cartService;}

    @PostMapping("/addItem")
    public ResponseEntity<?> addItemToCart(@RequestBody CartItemDTO itemBody) {
        try {
            cartService.addToCart(itemBody);
            return ResponseEntity.ok().body("Item added to cart successfully!");
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}
