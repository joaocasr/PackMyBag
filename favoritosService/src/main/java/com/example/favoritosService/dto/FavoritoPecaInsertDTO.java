package com.example.favoritosService.dto;

import java.util.Set;

public class FavoritoPecaInsertDTO {
    private String codigoItem;
    private String clienteUsername;

    public FavoritoPecaInsertDTO(String codigo, String cliente) {
        this.codigoItem = codigo;
        this.clienteUsername = cliente;
    }

    public String getCodigoItem() {
        return codigoItem;
    }

    public void setCodigoItem(String codigo) {
        this.codigoItem = codigo;
    }

    public String getClienteUsername() {
        return clienteUsername;
    }

    public void setClienteUsername(String clienteUsername) {
        this.clienteUsername = clienteUsername;
    }

}
