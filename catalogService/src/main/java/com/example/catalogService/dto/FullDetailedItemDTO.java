package com.example.catalogService.dto;

import java.util.List;

public class FullDetailedItemDTO {
    private int IDItem;
    private String designacao;
    private double preco;
    private String cor;
    private String imagem;
    private String tipo;
    private String tamanho;
    private String disponibilidade;
    private int nrReviews;
    private List<RelacionadosDTO> relacionados;
    //lista de sets em que a peca está presente ou pecas que constitui o set


    public FullDetailedItemDTO() {
    }

    public FullDetailedItemDTO(int id, String designacao, double preco, String cor, String imagem, String tipo, String tamanho, String disponibilidade, int nrReviews, List<RelacionadosDTO> relacionados ) {
        this.IDItem = id;
        this.designacao = designacao;
        this.preco = preco;
        this.cor = cor;
        this.imagem = imagem;
        this.tipo = tipo;
        this.tamanho = tamanho;
        this.disponibilidade = disponibilidade;
        this.nrReviews = nrReviews;
        this.relacionados = relacionados;
    }

    public int getIDItem() {
        return IDItem;
    }

    public void setIDItem(int IDItem) {
        this.IDItem = IDItem;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(String disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public int getNrReviews() {
        return nrReviews;
    }

    public void setNrReviews(int nrReviews) {
        this.nrReviews = nrReviews;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public List<RelacionadosDTO> getRelacionados() {
        return relacionados;
    }

    public void setRelacionados(List<RelacionadosDTO> relacionados) {
        this.relacionados = relacionados;
    }
}
