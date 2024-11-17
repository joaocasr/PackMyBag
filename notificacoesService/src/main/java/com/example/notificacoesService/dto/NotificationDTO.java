package com.example.notificacoesService.dto;

public class NotificationDTO {
    private String tipo;
    private String descricao;
    private String data;
    private Integer id;

    public NotificationDTO(String tipo, String descricao, String data, Integer id) {
        this.tipo = tipo;
        this.descricao = descricao;
        this.data = data;
        this.id = id;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
}
