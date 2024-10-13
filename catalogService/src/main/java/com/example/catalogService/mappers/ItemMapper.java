package com.example.catalogService.mappers;

import com.example.catalogService.dto.CatalogoItemDTO;
import com.example.catalogService.model.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {
    public CatalogoItemDTO toCatalogoItemDTO(Item item){
        return new CatalogoItemDTO(item.getDesignacao(),item.getPreco(),item.getCor(),item.getImagem(), item.getGenero());
    }
}
