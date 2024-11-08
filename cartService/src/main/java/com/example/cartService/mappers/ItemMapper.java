package com.example.cartService.mappers;

import com.example.cartService.dto.ItemDTO;
import com.example.cartService.model.Item;

public class ItemMapper {
    
    public static ItemDTO toDTO(Item item) {
        return new ItemDTO(
            item.getCodigo(),
            item.getIdLoja(),
            item.getQuantidade()
        );
    }
    
    public static Item toEntity(ItemDTO dto) {
        Item item = new Item();
        item.setCodigo(dto.getCodigo());
        item.setIdLoja(dto.getIdloja());
        item.setQuantidade(dto.getQuantidade());
        return item;
    }
}