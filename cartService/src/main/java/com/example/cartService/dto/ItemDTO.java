package com.example.cartService.dto;

public class ItemDTO {
    private String codigo;
    private String designacao;
    private double preco;
    private int quantidade; 

    public ItemDTO() {
    }

    public ItemDTO(String codigo, 
                   String designacao, 
                   double preco, 
                   int quantidade) {
        this.codigo = codigo;
        this.designacao = designacao;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
