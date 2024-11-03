package com.example.notificacoesService.dto;

public class EncomendaStatusDTO {
    private String codigoEncomenda;
    private String status;

    // Getters e Setters

    public String getCodigoEncomenda() { return codigoEncomenda; }
    public void setCodigoEncomenda(String codigo) { this.codigoEncomenda = codigo; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}