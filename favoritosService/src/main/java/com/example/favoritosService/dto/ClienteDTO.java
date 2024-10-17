package com.example.favoritosService.dto;

public class ClienteDTO {
    private String username;

    public ClienteDTO() {
    }

    public ClienteDTO(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
