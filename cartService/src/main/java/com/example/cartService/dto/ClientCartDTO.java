package com.example.cartService.dto;

public class ClientCartDTO {
    private String nome;
    private String username;
    private String email;
    private CartDTO cart;

    public ClientCartDTO() {
    }

    public ClientCartDTO(String nome, String username, String email, CartDTO cart) {
        this.nome = nome;
        this.username = username;
        this.email = email;
        this.cart = cart;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CartDTO getCart() {
        return cart;
    }

    public void setCarts(CartDTO cart) {
        this.cart = cart;
    }
}
