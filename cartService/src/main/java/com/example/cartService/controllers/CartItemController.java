package com.example.cartService.controllers;

import java.util.List;
import java.util.Set;

import com.example.cartService.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cartService.model.Pagamento;
import com.example.cartService.services.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartItemController {
    
    private final CartService cartService;

    @Autowired
    public CartItemController(CartService cartService){this.cartService=cartService;}

    @GetMapping("/{username}") //a funcionar
    public List<CartItemDTO> getCartItems(@PathVariable String username) {
        return cartService.getCartItems(username);
    }

    @PostMapping("/addItem") //a funcionar
    public ResponseEntity<?> addItemToCart(@RequestBody CartItemInsertDTO itemBody) {
        try {
            cartService.addToCart(itemBody);
            return ResponseEntity.ok().body("Item added to cart successfully!");
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/removeItem") //a funcionar
    public ResponseEntity<?> removeItemFromCart(@RequestBody CartItemRemoveDTO itemBody) {
        try {
            cartService.removeFromCart(itemBody);
            return ResponseEntity.ok().body("Item removed from cart successfully!");
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/clearCart") //a funcionar
    public ResponseEntity<?> clearCart(@RequestBody String username) {
        try {
            cartService.clearCart(username);
            return ResponseEntity.ok().body("Cart cleared successfully!");
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/changeQuantity") //a funcionar
    public ResponseEntity<?> changeItemQuantity(@RequestBody CartItemChangeQuantityDTO itemBody) {
        try {
            cartService.changeItemQuantity(itemBody);
            return ResponseEntity.ok().body("Item quantity updated successfully!");
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/count/{username}") //a funcionar
    public int getCartItemsCount(@PathVariable String username) {
        return cartService.getCartItemsCount(username);
    }

    @PostMapping("/createPayment")
    public ResponseEntity<?> createPayment(@RequestBody FormPaymentDTO paymentInfo) {
        try {
            cartService.createFormPayment(paymentInfo);
            return ResponseEntity.ok().body("Payment created successfully!");
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/createPaymentCartClean")
    public ResponseEntity<?> createPaymentCartClean(@RequestBody CartPaymentDTO paymentInfo) {
        try {
            cartService.createPayment(paymentInfo);
            cartService.clearCart(paymentInfo.getUsername());
            return ResponseEntity.ok().body("Payment created successfully!");
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/transactions/{username}") //a funcionar
    public Set<PagamentoDTO> getUserTransactions(@PathVariable String username) {
        return cartService.getUserTransactions(username);
    }

    @GetMapping("/transaction/{code}") //a funcionar
    public PagamentoEncomendaDTO getTransaction(@PathVariable String code) {
        return cartService.getPagamentoByCode(code);
    }

    @PostMapping("/changePaymentStatus")
    public ResponseEntity<?> changePaymentStatus(@RequestBody CartPaymentStatusChangeDTO paymentInfo) {
        try {
            cartService.changePaymentStatus(paymentInfo);
            return ResponseEntity.ok().body("Payment status updated successfully!");
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
/*
    @PostMapping("/processPayPalPayment")
    public ResponseEntity<?> processPayPalPayment(@RequestBody CartPaymentPaypalDTO paymentInfo) {
        try {
            cartService.processPayPalPayment(paymentInfo);
            return ResponseEntity.ok().body("Payment processed successfully!");
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }*/
}
