package com.example.cartService.mappers;

import com.example.cartService.dto.CartDTO;
import com.example.cartService.dto.CartItemDTO;
import com.example.cartService.dto.ClientCartDTO;
import com.example.cartService.model.Cart;
import com.example.cartService.model.Cliente;

import java.util.List;
import java.util.stream.Collectors;

public class ClientCartMapper {
    
    public ClientCartDTO toClientCartDTO(Cliente cliente) {
        return new ClientCartDTO(
            cliente.getNome(),
            cliente.getUsername(),
            cliente.getEmail(),
            cartToDTO(cliente.getCart())
        );
    }

    private static CartDTO cartToDTO(Cart cart) {
        List<CartItemDTO> itemDTOs = cart.getItens().stream()
            .map(CartItemMapper::toCartItemDTO)
            .collect(Collectors.toList());
        return new CartDTO(itemDTOs);
    }
}
