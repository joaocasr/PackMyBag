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
import java.util.List;
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

    public ClientCartDTO getUserCart(String username) throws NoClientException {
        Cliente cliente = clientCartRepository.getClienteByUsername(username);
        if (cliente == null) {
            throw new NoClientException("Client not found with username: " + username);
        }
        return clientCartMapper.toClientCartDTO(cliente);
    }

    public List<CartItemDTO> getCartItems(String username) throws NoClientException {
        Cliente cliente = clientCartRepository.getClienteByUsername(username);
        if (cliente == null) {
            throw new NoClientException("Client not found with username: " + username);
        }
        return cliente.getCart().getItens().stream()
            .map(CartItemMapper::toCartItemDTO)
            .collect(Collectors.toList());
    }

    public void addToCart(CartItemInsertDTO item) throws NoClientException {
        Cliente cliente = clientCartRepository.getClienteByUsername(item.getUsername());

        if (cliente == null) {
            throw new NoClientException("Client not found with username: " + item.getUsername());
        }

        Cart cart = cliente.getCart();
        if (cart == null) {
            // Create new cart if user doesn't have one
            cart = new Cart();
            cliente.setCart(cart);
        }

        // Check if item already exists
        boolean itemExists = false;
        for (Item existingItem : cart.getItens()) {
            if (existingItem.getCodigo().equals(item.getCodigo())) {
                // Update quantity if item exists
                existingItem.setQuantidade(existingItem.getQuantidade() + item.getQuantidade());
                itemExists = true;
                break;
            }
        }

        if (!itemExists) {
            // Add new item
            Item cartItem = new Item();
            cartItem.setCodigo(item.getCodigo());
            cartItem.setDesignacao(item.getDesignacao());
            cartItem.setPreco(item.getPreco());
            cartItem.setQuantidade(item.getQuantidade());
            cart.getItens().add(cartItem);
        }

        clientCartRepository.save(cliente);
    }
    
    /**
     * Changes the quantity of an item in the cart,
     * doesn't check if the quantity passed is valid,
     * that's done in the controller
     * @param itemChange
     * @throws NoClientException
     */
    public void changeItemQuantity(CartItemChangeQuantityDTO itemChange) throws NoClientException {
        Cliente cliente = clientCartRepository.getClienteByUsername(itemChange.getUsername());
        if (cliente == null) {
            throw new NoClientException("Client not found with username: " + itemChange.getUsername());
        }

        Cart cart = cliente.getCart();
        if (cart == null) {
            throw new NoCartException("Cart not found for client: " + itemChange.getUsername());
        }

        Item existingItem = cart.getItens().stream()
            .filter(i -> i.getCodigo().equals(itemChange.getCodigo()))
            .findFirst()
            .orElse(null);

        if (existingItem == null) {
            throw new NoItemException("Item not found in cart: " + itemChange.getCodigo());
        }

        addToCart(new CartItemInsertDTO(itemChange.getCodigo(), itemChange.getUsername(), 
                                      existingItem.getDesignacao(), existingItem.getPreco(), 
                                      itemChange.getQuantity()));
    }

    public void removeFromCart(CartItemRemoveDTO itemToRemove) throws NoClientException {
        Cliente cliente = clientCartRepository.getClienteByUsername(itemToRemove.getUsername());
        if (cliente == null) {
            throw new NoClientException("Client not found with username: " + itemToRemove.getUsername());
        }

        Cart cart = cliente.getCart();
        if (cart == null) {
            throw new NoCartException("Cart not found for client: " + itemToRemove.getUsername());
        }

        cart.getItens().removeIf(cartItem -> cartItem.getCodigo().equals(itemToRemove.getCodigo()));
        clientCartRepository.save(cliente);
    }

    public void clearCart(String username) throws NoClientException {
        Cliente cliente = clientCartRepository.getClienteByUsername(username);
        if (cliente == null) {
            throw new NoClientException("Client not found with username: " + username);
        }

        cliente.setCart(null);
        clientCartRepository.save(cliente);
    }

    public int getCartItemsCount(String username) throws NoClientException {
        Cliente cliente = clientCartRepository.getClienteByUsername(username);
        if (cliente == null) {
            throw new NoClientException("Client not found with username: " + username);
        }
        return cliente.getCart().getItens().stream()
                .mapToInt(Item::getQuantidade)
                .sum();
    }
}