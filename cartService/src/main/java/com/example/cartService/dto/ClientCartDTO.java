package com.example.cartService.dto;

import java.util.Set;

public class ClientCartDTO {
    private String nome;
    private String username;
    private String email;
    private Set<CartDTO> carts;

    public ClientCartDTO() {
    }

    public ClientCartDTO(String nome, String username, String email, Set<CartDTO> carts) {
        this.nome = nome;
        this.username = username;
        this.email = email;
        this.carts = carts;
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

    public Set<CartDTO> getCarts() {
        return carts;
    }

    public void setCarts(Set<CartDTO> carts) {
        this.carts = carts;
    }
}
