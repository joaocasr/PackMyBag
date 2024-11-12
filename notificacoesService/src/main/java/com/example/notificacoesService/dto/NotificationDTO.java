package com.example.notificacoesService.dto;

public class NotificationDTO {
    private String tipo;
    private String descricao;
    private String data;

    public NotificationDTO(String tipo, String descricao, String data) {
        this.tipo = tipo;
        this.descricao = descricao;
        this.data = data;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getData() {
        return data;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setData(String data) {
        this.data = data;
    }
    
}
