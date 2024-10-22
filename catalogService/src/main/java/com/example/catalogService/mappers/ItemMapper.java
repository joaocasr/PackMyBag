package com.example.catalogService.mappers;

import com.example.catalogService.dto.CatalogoItemDTO;
import com.example.catalogService.dto.FullDetailedItemDTO;
import com.example.catalogService.dto.RelacionadosDTO;
import com.example.catalogService.dto.ReviewDTO;
import com.example.catalogService.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class ItemMapper {
    public CatalogoItemDTO toCatalogoItemDTO(Item item){
        return new CatalogoItemDTO(item.getORMID(),item.getDesignacao(),item.getPreco(),item.getCor(),item.getImagem(), item.getTipo());
    }

    public FullDetailedItemDTO toFullCatalogoDTO(Item item,int nrreviews){
        String tamanho="";
        List<RelacionadosDTO> relacionados = new ArrayList<>();
        if(item instanceof Peca){
            tamanho = ((Peca) item).getTamanho();
            relacionados = ((Peca) item).getSets().stream().limit(4).map(x->new RelacionadosDTO(x.getDesignacao(),x.getImagem(),x.getIDItem())).toList();
        }
        if(item instanceof Set) {
            tamanho = ((Set) item).getTamanho();
            relacionados = ((Set) item).getPecas().stream().limit(4).map(x->new RelacionadosDTO(x.getDesignacao(),x.getImagem(),x.getIDItem())).toList();
        }
        if(item instanceof Calcado) tamanho = String.valueOf(((Calcado) item).getNumero());
        Loja j = item.getLoja();
        return new FullDetailedItemDTO(item.getORMID(),item.getDesignacao(),item.getPreco(),item.getCor(),item.getImagem(), item.getTipo(), tamanho,item.getDisponibilidade(),nrreviews,relacionados,j);
    }

    public ReviewDTO toReviewDTO(Review review){
        return new ReviewDTO(review.getClassificacao(),review.getAutor().getNome(), review.getDescricao(), review.getAutor().getProfileImg(),review.getData());
    }

}
