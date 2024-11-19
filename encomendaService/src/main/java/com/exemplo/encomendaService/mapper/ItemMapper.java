package com.exemplo.encomendaService.mapper;

import org.springframework.stereotype.Component;

import com.exemplo.encomendaService.dto.ItemDTO;
import com.exemplo.encomendaService.model.Item;

@Component
public class ItemMapper {

    // Converte de Entidade para DTO
    public static ItemDTO toDTO(Item item) {
        ItemDTO dto = new ItemDTO();
        dto.setCodigo(item.getCodigo());
        dto.setNrPedido(item.getNrpedido());
        dto.setDesignacao(item.getDesignacao());
        dto.setImagem(item.getImagemUrl());
        dto.setPreco(item.getPreco());
        dto.setIdLoja(item.getIdLoja());
        return dto;
    }

    // Converte de DTO para Entidade
    public static Item toEntity(ItemDTO dto) {
        System.out.println("aquiii");

        Item item = new Item();
        item.setCodigo(dto.getCodigo());
        item.setNrpedido(dto.getNrPedido());
        item.setIdLoja(dto.getIdLoja());
        item.setDesignacao(dto.getDesignacao());
        item.setImagemUrl(dto.getImagem());
        item.setPreco(dto.getPreco());
        System.out.println(item);

        return item;
    }
}
