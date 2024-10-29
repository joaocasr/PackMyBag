package com.example.cartService.dto;

import java.util.List;

public class CartDTO {
    private java.util.List<ItemDTO> itens;

    public CartDTO() {
    }

    public CartDTO(List<ItemDTO> itens) {
        this.itens = itens;
    }

    public List<ItemDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemDTO> itens) {
        this.itens = itens;
    }
}
