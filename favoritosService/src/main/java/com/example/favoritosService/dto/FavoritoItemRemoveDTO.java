package com.example.favoritosService.dto;

public class FavoritoItemRemoveDTO {
    private String username;
    private String itemCode;
    private int idLoja;

    public FavoritoItemRemoveDTO(String username, String itemCode, int idLoja) {
        this.username = username;
        this.itemCode = itemCode;
        this.idLoja = idLoja;
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

    public int getIdLoja() {
        return idLoja;
    }

    public void setIdLoja(int idLoja) {
        this.idLoja = idLoja;
    }
}
