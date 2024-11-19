package com.example.recomendacoesservice.dto;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.recomendacoesservice.model.Pedido;

public class recomendacaoToClienteDTO {
    private String descricao;
    private Set<itemDTO> conjunto;

    public recomendacaoToClienteDTO(String descricao, Set<itemDTO> conjunto) {
        this.descricao = descricao;
        this.conjunto = conjunto;
    }

    public recomendacaoToClienteDTO(Pedido p) {
        this.descricao = p.getDescricao();
        this.conjunto = p.getConjunto().stream().map(x -> new itemDTO(x)).collect(Collectors.toSet());
    }

    public String getDescricao() {
        return this.descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public Set<itemDTO> getConjunto() {
        return this.conjunto;
    }
    public void setConjunto(Set<itemDTO> conjunto) {
        this.conjunto = conjunto;
    }
}
