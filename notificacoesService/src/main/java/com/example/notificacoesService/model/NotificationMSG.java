package com.example.notificacoesService.model;

public class NotificationMSG {
    private String tipo;
    private String descricao;
    private String data;
    private String username;

    public NotificationMSG(String tipo, String descricao, String data, String username) {
        this.tipo = tipo;
        this.descricao = descricao;
        this.data = data;
        this.username = username;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
