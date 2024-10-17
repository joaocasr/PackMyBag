package com.example.catalogService.mappers;

import com.example.catalogService.dto.CatalogoItemDTO;
import com.example.catalogService.dto.FullDetailedItemDTO;
import com.example.catalogService.model.Calcado;
import com.example.catalogService.model.Item;
import com.example.catalogService.model.Peca;
import com.example.catalogService.model.Set;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ItemMapper {
    public CatalogoItemDTO toCatalogoItemDTO(Item item){
        return new CatalogoItemDTO(item.getORMID(),item.getDesignacao(),item.getPreco(),item.getCor(),item.getImagem(), item.getTipo());
    }

    public FullDetailedItemDTO toFullCatalogoDTO(Item item){
        String tamanho="";
        if(item instanceof Peca) tamanho = ((Peca) item).getTamanho();
        if(item instanceof Set) tamanho = ((Set) item).getTamanho();
        if(item instanceof Calcado) tamanho = String.valueOf(((Calcado) item).getNumero());

        return new FullDetailedItemDTO(item.getORMID(),item.getDesignacao(),item.getPreco(),item.getCor(),item.getImagem(), item.getTipo(), tamanho,item.getDisponibilidade(),item.getCriticas().size(),item.getCriticas().stream().toList());
    }

}
