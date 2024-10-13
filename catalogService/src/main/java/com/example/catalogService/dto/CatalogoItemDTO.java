package com.example.catalogService.dto;

public class CatalogoItemDTO {
    private String designacao;
    private double preco;
    private String cor;
    private String imagem;
    private String genero;

    public CatalogoItemDTO(String designacao, double preco, String cor, String imagem, String genero) {
        this.designacao = designacao;
        this.preco = preco;
        this.cor = cor;
        this.imagem = imagem;
        this.genero = genero;
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

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
