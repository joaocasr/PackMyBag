package com.example.catalogService.dto;

public class TrendingItemDTO {
    private String nome;
    private String imagem;
    private int nrAquisicoes;
    private int idItem;

    public TrendingItemDTO(String nome, String imagem, int nrAquisicoes, int idItem) {
        this.nome = nome;
        this.imagem = imagem;
        this.nrAquisicoes = nrAquisicoes;
        this.idItem = idItem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public int getNrAquisicoes() {
        return nrAquisicoes;
    }

    public void setNrAquisicoes(int nrAquisicoes) {
        this.nrAquisicoes = nrAquisicoes;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }
}
