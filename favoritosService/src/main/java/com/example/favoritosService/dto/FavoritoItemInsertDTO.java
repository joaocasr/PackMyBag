package com.example.favoritosService.dto;

public class FavoritoItemInsertDTO {
    private String codigoItem;
    private String clienteUsername;
    private String designacao;
    private double preco;
    private String disponibilidade;
    private String tipo;
    private String imagem;
    private String subclasse;
    private String dimensao; // 25-40 ou S,M,L...


    public FavoritoItemInsertDTO(String codigoItem, String clienteUsername, String designacao, double preco, String disponibilidade, String tipo, String imagem, String subclasse, String dimensao) {
        this.codigoItem = codigoItem;
        this.clienteUsername = clienteUsername;
        this.designacao = designacao;
        this.preco = preco;
        this.disponibilidade = disponibilidade;
        this.tipo = tipo;
        this.imagem = imagem;
        this.subclasse = subclasse;
        this.dimensao = dimensao;
    }

    public String getSubclasse() {
        return subclasse;
    }

    public void setSubclasse(String subclasse) {
        this.subclasse = subclasse;
    }

    public String getDimensao() {
        return dimensao;
    }

    public void setDimensao(String dimensao) {
        this.dimensao = dimensao;
    }

    public String getCodigoItem() {
        return codigoItem;
    }

    public void setCodigoItem(String codigo) {
        this.codigoItem = codigo;
    }

    public String getClienteUsername() {
        return clienteUsername;
    }

    public void setClienteUsername(String clienteUsername) {
        this.clienteUsername = clienteUsername;
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

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
