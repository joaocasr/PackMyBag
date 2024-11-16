package com.example.cartService.dto;

import java.util.List;

public class FreeResourcesDTO {
    private List<ItemDTO> itens;

    public FreeResourcesDTO() {
    }

    public FreeResourcesDTO(List<ItemDTO> itens) {
        this.itens = itens;
    }

    public List<ItemDTO> getitens() {
        return itens;
    }

    public void setItemsencomenda(List<ItemDTO> itens) {
        this.itens = itens;
    }
}
