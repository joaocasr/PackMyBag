package com.example.cartService.dto;

public class CartItemDTO {
    private String codigo;
    private Integer nraquisicoes;
    private String imagem;
    private Double price;
    private String designacao;
    
    public CartItemDTO() {
    }
    
    public CartItemDTO(String codigo, Integer nraquisicoes, String imagem, Double price, String designacao) {
        this.codigo = codigo;
        this.nraquisicoes = nraquisicoes;
        this.imagem = imagem;
        this.price = price;
        this.designacao = designacao;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getNraquisicoes() {
        return nraquisicoes;
    }

    public void setNraquisicoes(Integer nraquisicoes) {
        this.nraquisicoes = nraquisicoes;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
    
    public Double getPrice() {
        return price;
    }
    
    public void setPrice(Double price) {
        this.price = price;
    }
    
    public String getDesignacao() {
        return designacao;
    }
    
    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }
}