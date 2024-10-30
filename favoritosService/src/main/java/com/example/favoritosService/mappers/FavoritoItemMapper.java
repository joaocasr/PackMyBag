package com.example.favoritosService.mappers;

import com.example.favoritosService.dto.FavoritoItemDTO;
import com.example.favoritosService.model.Item;
import com.example.favoritosService.dto.FavoritoItemDTO;
import org.springframework.stereotype.Component;

@Component
public class FavoritoItemMapper {

    public FavoritoItemDTO toFavoritoItemDTO(Item item){
        return new FavoritoItemDTO(item.getCodigo(),item.getDesignacao(), item.getPreco(), item.getImagem(), item.getTipo(),item.getDisponibilidade(), item.getIdentificador(),item.getIdLoja());
    }


}
