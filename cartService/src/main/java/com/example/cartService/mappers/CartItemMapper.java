package com.example.cartService.mappers;

import org.springframework.stereotype.Component;
import com.example.cartService.dto.CartItemDTO;
import com.example.cartService.model.Item;

@Component
public class CartItemMapper {
    public CartItemDTO toCartItemDTO(Item item) {
        return new CartItemDTO(
            item.getCodigo(),
            item.getNraquisicoes(),
            item.getPreco(),
            item.getDesignacao()
        );
    }
} 