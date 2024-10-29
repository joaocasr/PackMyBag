package com.example.cartService.mappers;

import com.example.cartService.dto.ItemDTO;
import com.example.cartService.model.Item;

public class ItemMapper {
    
    public static ItemDTO toDTO(Item item) {
        return new ItemDTO(
            item.getCodigo(),
            item.getDesignacao(), 
            item.getPreco(),
            item.getNraquisicoes(),
            item.getEstilo(),
            item.getCor(),
            item.getTamanho(),
            item.getGenero(),
            item.getDisponibilidade(),
            item.getImagem()
        );
    }
    
    public static Item toEntity(ItemDTO dto) {
        Item item = new Item();
        item.setCodigo(dto.getCodigo());
        item.setDesignacao(dto.getDesignacao());
        item.setPreco(dto.getPreco());
        item.setNraquisicoes(dto.getNraquisicoes());
        item.setEstilo(dto.getEstilo());
        item.setCor(dto.getCor());
        item.setTamanho(dto.getTamanho());
        item.setGenero(dto.getGenero());
        item.setDisponibilidade(dto.getDisponibilidade());
        item.setImagem(dto.getImagem());
        return item;
    }
}