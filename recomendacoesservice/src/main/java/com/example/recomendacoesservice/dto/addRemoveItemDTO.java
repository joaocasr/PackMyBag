package com.example.recomendacoesservice.dto;

public class addRemoveItemDTO {
    private String nome;
    private itemDTO item;

    public addRemoveItemDTO(String nome, itemDTO item) {
        this.nome = nome == null ? "" : nome;
        this.item = item;
    }

    public String getNome() {
        return this.nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public itemDTO getItem() {
        return this.item;
    }
    public void setItem(itemDTO item) {
        this.item = item;
    }
}
