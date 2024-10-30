package com.example.cartService.mappers;

import org.springframework.stereotype.Component;
import com.example.cartService.dto.CartItemDTO;
import com.example.cartService.model.Item;

@Component
public class CartItemMapper {
    public static CartItemDTO toCartItemDTO(Item item) {
        return new CartItemDTO(
            item.getCodigo(),
            item.getQuantidade(),
            item.getPreco(),
            item.getDesignacao()
        );
    }
} 