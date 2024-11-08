package com.example.cartService.dto;

public class CartItemRemoveDTO {
    private String codigo;
    private int idloja;
    private String username;

    public CartItemRemoveDTO(String codigo, int idloja, String username) {
        this.codigo = codigo;
        this.idloja = idloja;
        this.username = username;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getIdLoja() {
        return idloja;
    }

    public void setIDLoja(int idloja) {
        this.idloja = idloja;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
