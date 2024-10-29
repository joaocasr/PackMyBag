package com.example.cartService.services;

import org.springframework.stereotype.Service;

import com.example.cartService.dto.*;
import com.example.cartService.exceptions.*;
import com.example.cartService.model.*;
import com.example.cartService.mappers.*;
import com.example.cartService.repositories.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/*
 * Most things here are a work in progress
 * take it with a grain of salt
 */

@Service
public class CartService {
    
    private final ClientCartRepository clientCartRepository;
    private final ClientCartMapper clientCartMapper;
    private final CartItemMapper cartItemMapper;

    public CartService(ClientCartRepository clientCartRepository, ClientCartMapper clientCartMapper, CartItemMapper cartItemMapper) {
        this.clientCartRepository = clientCartRepository;
        this.clientCartMapper = clientCartMapper;
        this.cartItemMapper = cartItemMapper;
    }

    public Set<CartItemDTO> getUserCart(String username, int page, int number) throws NoCartItemsException {
        return clientCartRepository.getCartByUsername(username, PageRequest.of(page, number))
                .stream()
                .flatMap(cart -> cart.getItens().stream())
                .map(cartItemMapper::toCartItemDTO)
                .collect(Collectors.toSet());
    }

    public void addToCart(CartItemInsertDTO item) {
        Set<Cart> carts = clientCartRepository.getCartByUsername_Set(item.getUsername());
        if (carts.isEmpty()) {
            // Create new cart if user doesn't have one
            Cart cart = new Cart(new HashSet<>());
            Item cartItem = new Item(item.getCodigo(), item.getdesignacao(), item.getPrice(), item.getQuantity());
            cart.addItem(cartItem);
            clientCartRepository.save(cart);
        } else {
            // Get existing cart
            Cart cart = clientCartRepository.getCartByUsername(item.getUsername());
            
            // Check if item already exists
            Optional<Item> existingItem = cart.getItems().stream()
                .filter(i -> i.getCodigo().equals(item.getCodigo()))
                .findFirst();
                
            if (existingItem.isPresent()) {
                // Update quantity if item exists
                Item cartItem = existingItem.get();
                cartItem.setNraquisicoes(cartItem.getNraquisicoes() + item.getQuantity());
            } else {
                // Add new item
                Item cartItem = new Item(item.getCodigo(), item.getdesignacao(), item.getPrice(), item.getQuantity());
                cart.addItem(cartItem);
            }
            
            clientCartRepository.save(cart);
        }
    }
}