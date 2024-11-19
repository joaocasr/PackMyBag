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
        dto.setDesignacao(item.getDesignacao());
        dto.setPreco(item.getPreco());
        dto.setNrPedido(item.getNrpedido());
        dto.setIdLoja(item.getIdLoja());
        return dto;
    }

    // Converte de DTO para Entidade
    public static Item toEntity(ItemDTO dto) {
        Item item = new Item();
        item.setCodigo(dto.getCodigo());
        item.setDesignacao(dto.getDesignacao());
        item.setPreco(dto.getPreco());
        item.setNrpedido(dto.getNrPedido());
        item.setIdLoja(dto.getIdLoja());
        return item;
    }
}
