package com.example.favoritosService.dto;

public class FavoritoItemRemoveDTO {
    private String username;
    private String itemCode;

    public FavoritoItemRemoveDTO(String username, String itemCode) {
        this.username = username;
        this.itemCode = itemCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }
}
