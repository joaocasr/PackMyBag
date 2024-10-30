package com.example.cartService.dto;

import java.util.List;

public class CartDTO {
    private List<CartItemDTO> itens;

    public CartDTO() {
    }

    public CartDTO(List<CartItemDTO> itens) {
        this.itens = itens;
    }

    public List<CartItemDTO> getItens() {
        return itens;
    }

    public void setItens(List<CartItemDTO> itens) {
        this.itens = itens;
    }
}
