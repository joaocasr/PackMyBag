package com.example.notificacoesService.dto;

public class ItemUpdate {
    private String code;
    private int idloja;
    private String disponibilidade;

    public ItemUpdate() {
    }

    public ItemUpdate(int idloja, String code, String disponibilidade) {
        this.idloja = idloja;
        this.code = code;
        this.disponibilidade = disponibilidade;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getIdloja() {
        return idloja;
    }

    public void setIdloja(int idloja) {
        this.idloja = idloja;
    }

    public String getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(String disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    @Override
    public String toString() {
        return "ItemUpdate,"+code  + ","
                + idloja + ","
                + disponibilidade;
    }
}
