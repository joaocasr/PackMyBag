package com.example.cartService.dto;

public class CartItemRemoveDTO {
    private String codigo;
    private String username;

    public CartItemRemoveDTO(String codigo, String username) {
        this.codigo = codigo;
        this.username = username;
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
}
