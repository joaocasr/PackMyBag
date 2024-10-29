package com.example.cartService.mappers;

import com.example.cartService.dto.CartDTO;
import com.example.cartService.dto.ClientCartDTO;
import com.example.cartService.model.Cart;
import com.example.cartService.model.Cliente;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ClientCartMapper {
    
    public static ClientCartDTO toDTO(Cliente cliente) {
        Set<CartDTO> cartDTOs = cliente.getCarts().stream()
            .map(ClientCartMapper::cartToDTO)
            .collect(Collectors.toSet());
            
        return new ClientCartDTO(
            cliente.getNome(),
            cliente.getUsername(),
            cliente.getEmail(),
            cartDTOs
        );
    }
    
    private static CartDTO cartToDTO(Cart cart) {
        return new CartDTO(
            cart.getItens().stream()
                .map(ItemMapper::toDTO)
                .collect(Collectors.toList())
        );
    }
    
    public static Cliente toEntity(ClientCartDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNome(dto.getNome());
        cliente.setUsername(dto.getUsername());
        cliente.setEmail(dto.getEmail());
        
        Set<Cart> carts = new HashSet<>();
        if (dto.getCarts() != null) {
            carts = dto.getCarts().stream()
                .map(cartDTO -> {
                    Cart cart = new Cart();
                    cart.setItens(cartDTO.getItens().stream()
                        .map(ItemMapper::toEntity)
                        .collect(Collectors.toList()));
                    return cart;
                })
                .collect(Collectors.toSet());
        }
        cliente.setCarts(carts);
        
        return cliente;
    }
}
