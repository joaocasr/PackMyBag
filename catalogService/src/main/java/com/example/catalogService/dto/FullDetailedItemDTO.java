package com.example.catalogService.dto;

import com.example.catalogService.model.Loja;

import java.util.List;

public class FullDetailedItemDTO {
    private int IDItem;
    private String codigo;
    private String designacao;
    private double preco;
    private String cor;
    private String imagem;
    private String tipo;
    private String tamanho;
    private String disponibilidade;
    private int averageRating;
    private int nrReviews;
    private String subclasse;
    private List<RelacionadosDTO> relacionados;
    //lista de sets em que a peca está presente ou pecas que constitui o set
    private Loja loja;

    public FullDetailedItemDTO() {
    }

    public FullDetailedItemDTO(int id, String codigo, int averageRating, String subclasse, String designacao, double preco, String cor, String imagem, String tipo, String tamanho, String disponibilidade, int nrReviews, List<RelacionadosDTO> relacionados, Loja j ) {
        this.IDItem = id;
        this.codigo = codigo;
        this.averageRating = averageRating;
        this.subclasse = subclasse;
        this.designacao = designacao;
        this.preco = preco;
        this.cor = cor;
        this.imagem = imagem;
        this.tipo = tipo;
        this.tamanho = tamanho;
        this.disponibilidade = disponibilidade;
        this.nrReviews = nrReviews;
        this.relacionados = relacionados;
        this.loja = j;
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

    public int getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(int averageRating) {
        this.averageRating = averageRating;
    }

    public String getSubclasse() {
        return subclasse;
    }

    public void setSubclasse(String subclasse) {
        this.subclasse = subclasse;
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public Loja getLoja() {
        return loja;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }
}
