package com.example.catalogService.dto;

import java.util.List;

public class EncomendaDTO {
    private List<ItemEncomenda> itens;

    public EncomendaDTO(List<ItemEncomenda> itens) {
        this.itens = itens;
    }

    public EncomendaDTO() {
    }

    public List<ItemEncomenda> getItens() {
        return itens;
    }

    public void setItens(List<ItemEncomenda> itens) {
        this.itens = itens;
    }
}
