package com.example.favoritosService.dto;

public class FavoritoItemDTO {
    private String designacao;
    private double preco;
    private String imagem;
    private String tipo;

    public FavoritoItemDTO(String designacao, double preco, String imagem,String tipo) {
        this.designacao = designacao;
        this.preco = preco;
        this.imagem = imagem;
        this.tipo = tipo;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
