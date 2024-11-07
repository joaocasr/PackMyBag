package com.example.cartService.dto;

public class CartItemChangeQuantityDTO {
    private String username;
    private String codigo;
    private int quantidade;

    public CartItemChangeQuantityDTO(String username, String codigo, int quantidade) {
        this.username = username;
        this.codigo = codigo;
        this.quantidade = quantidade;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getQuantity() {
        return quantidade;
    }

    public void setQuantity(int quantidade) {
        this.quantidade = quantidade;
    }
}
