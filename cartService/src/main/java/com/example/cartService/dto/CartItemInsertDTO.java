package com.example.cartService.dto;

public class CartItemInsertDTO {
    private String codigo;
    private String username;
    private String designacao;
    private double price;
    private int quantity;

    public CartItemInsertDTO(String codigo, String username, String designacao, double price, int quantity) {
        this.codigo = codigo;
        this.username = username;
        this.designacao = designacao;
        this.price = price;
        this.quantity = quantity;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
