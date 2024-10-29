package com.example.cartService.dto;

public class CartItemDTO {
    private String codigo;
    private Integer quantity;
    private Double price;
    private String designacao;
    
    public CartItemDTO() {
    }
    
    public CartItemDTO(String codigo, Integer quantity, Double price, String designacao) {
        this.codigo = codigo;
        this.quantity = quantity;
        this.price = price;
        this.designacao = designacao;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
    public Double getPrice() {
        return price;
    }
    
    public void setPrice(Double price) {
        this.price = price;
    }
    
    public String getdesignacao() {
        return designacao;
    }
    
    public void setdesignacao(String designacao) {
        this.designacao = designacao;
    }
}