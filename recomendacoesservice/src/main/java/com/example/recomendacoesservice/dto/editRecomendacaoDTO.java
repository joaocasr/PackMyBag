package com.example.recomendacoesservice.dto;

import java.util.HashSet;
import java.util.Set;

public class editRecomendacaoDTO {

    private int IDRecomendacao;
    private String descricao;
    private String itemsEditType; // "addItems", "removeItems"
    private Set<itemDTO> conjunto;
    private String status;


    public editRecomendacaoDTO(int IDRecomendacao, String descricao, String itemsEditType, Set<itemDTO> conjunto, String status) {
        this.IDRecomendacao = IDRecomendacao;
        this.descricao = descricao == null ? "" : descricao;
        this.itemsEditType = itemsEditType == null ? "" : itemsEditType;
        this.conjunto = conjunto == null? new HashSet<>() : conjunto;
        this.status = status == null ? "" : status;
    }

    public int getIDRecomendacao() {
        return this.IDRecomendacao;
    }
    public void setIDRecomendacao(int IDRecomendacao) {
        this.IDRecomendacao = IDRecomendacao;
    }
    public String getDescricao() {
        return this.descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getItemsEditType() {
        return this.itemsEditType;
    }
    public void setItemsEditType(String itemsEditType) {
        this.itemsEditType = itemsEditType;
    }
    public Set<itemDTO> getConjunto() {
        return this.conjunto;
    }
    public void setConjunto(Set<itemDTO> conjunto) {
        this.conjunto = conjunto;
    }
    public String getStatus() {
        return this.status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
