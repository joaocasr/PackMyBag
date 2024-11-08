package com.example.recomendacoesservice.dto;

import com.example.recomendacoesservice.model.Recomendacao;

import java.util.Set;

public class recomendacaoDTO {
    private String descricao;
    private Set conjunto;
    private String status;

    public recomendacaoDTO(Recomendacao recomendacao) {
        this.descricao = recomendacao.getDescricao();
        this.conjunto = recomendacao.getConjunto();
        this.status = recomendacao.getStatus();
    }

    public recomendacaoDTO(String descricao, Set conjunto, String status) {
        this.descricao = descricao;
        this.conjunto = conjunto;
        this.status = status;
    }

    public String getDescricao() {
        return this.descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public Set getConjunto() {
        return this.conjunto;
    }
    public void setConjunto(Set conjunto) {
        this.conjunto = conjunto;
    }
    public String getStatus() {
        return this.status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
