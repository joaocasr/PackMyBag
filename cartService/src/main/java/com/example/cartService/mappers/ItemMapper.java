package com.example.cartService.mappers;

import com.example.cartService.dto.ItemDTO;
import com.example.cartService.model.Item;

public class ItemMapper {
    
    public static ItemDTO toDTO(Item item) {
        return new ItemDTO(
            item.getCodigo(),
            item.getDesignacao(), 
            item.getPreco(),
            item.getQuantidade()
        );
    }
    
    public static Item toEntity(ItemDTO dto) {
        Item item = new Item();
        item.setCodigo(dto.getCodigo());
        item.setDesignacao(dto.getDesignacao());
        item.setPreco(dto.getPreco());
        item.setQuantidade(dto.getQuantidade());
        return item;
    }
}