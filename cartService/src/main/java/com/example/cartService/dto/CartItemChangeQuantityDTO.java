package com.example.cartService.dto;

public class CartItemChangeQuantityDTO {
    private String username;
    private String codigo;
    private int quantity;

    public CartItemChangeQuantityDTO(String username, String codigo, int quantity) {
        this.username = username;
        this.codigo = codigo;
        this.quantity = quantity;
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
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
