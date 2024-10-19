package com.example.catalogService.mappers;

import com.example.catalogService.dto.CatalogoItemDTO;
import com.example.catalogService.dto.FullDetailedItemDTO;
import com.example.catalogService.dto.ReviewDTO;
import com.example.catalogService.model.*;
import org.springframework.stereotype.Component;


@Component
public class ItemMapper {
    public CatalogoItemDTO toCatalogoItemDTO(Item item){
        return new CatalogoItemDTO(item.getORMID(),item.getDesignacao(),item.getPreco(),item.getCor(),item.getImagem(), item.getTipo());
    }

    public FullDetailedItemDTO toFullCatalogoDTO(Item item,int nrreviews){
        String tamanho="";
        if(item instanceof Peca) tamanho = ((Peca) item).getTamanho();
        if(item instanceof Set) tamanho = ((Set) item).getTamanho();
        if(item instanceof Calcado) tamanho = String.valueOf(((Calcado) item).getNumero());

        return new FullDetailedItemDTO(item.getORMID(),item.getDesignacao(),item.getPreco(),item.getCor(),item.getImagem(), item.getTipo(), tamanho,item.getDisponibilidade(),nrreviews);
    }

    public ReviewDTO toReviewDTO(Review review){
        return new ReviewDTO(review.getClassificacao(),review.getAutor().getNome(), review.getDescricao(), review.getAutor().getProfileImg(),review.getData());
    }

}
