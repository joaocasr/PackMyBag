package com.example.catalogService.dto;

public class CatalogoItemDTO {
    private int IDitem;
    private String designacao;
    private double preco;
    private String cor;
    private String imagem;
    private String tipo;

    public CatalogoItemDTO(int id,String designacao, double preco, String cor, String imagem, String tipo) {
        this.IDitem = id;
        this.designacao = designacao;
        this.preco = preco;
        this.cor = cor;
        this.imagem = imagem;
        this.tipo = tipo;
    }

    public int getIDitem() {
        return IDitem;
    }

    public void setIDitem(int IDitem) {
        this.IDitem = IDitem;
    }

    public void setTipo(String tipo) {
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

    public String getTipo() {
        return tipo;
    }

    public void getTipo(String genero) {
        this.tipo = genero;
    }
}
