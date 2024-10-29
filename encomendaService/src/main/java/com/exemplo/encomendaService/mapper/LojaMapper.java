package com.exemplo.encomendaService.mapper;

import org.springframework.stereotype.Component;

import com.exemplo.encomendaService.dto.LojaDTO;
import com.exemplo.encomendaService.model.Loja;

@Component
public class LojaMapper {

    public static LojaDTO toDTO(Loja loja) {
        return new LojaDTO(loja.getIDLoja(), loja.getNome(), loja.getMarca(), loja.getLocalizacao());
    }

    public static Loja toEntity(LojaDTO lojaDTO) {
        Loja loja = new Loja();
        //loja.setIDLoja(lojaDTO.getIDLoja());
        loja.setNome(lojaDTO.getNome());
        loja.setMarca(lojaDTO.getMarca());
        loja.setLocalizacao(lojaDTO.getLocalizacao());
        return loja;
    }

}
