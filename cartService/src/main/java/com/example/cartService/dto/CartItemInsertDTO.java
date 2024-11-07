package com.example.cartService.dto;

public class CartItemInsertDTO {
    private String codigo;
    private String username;
    private String designacao;
    private String imagem;
    private double preco;
    private int quantidade;

    public CartItemInsertDTO(String codigo, String username, String designacao, String imagem,  double preco, int quantidade) {
        this.codigo = codigo;
        this.username = username;
        this.designacao = designacao;
        this.imagem = imagem;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
