package com.example.notificacoesService.dto;

public class EncomendaStatusDTO {
    private int idEncomenda;
    private String codigoEncomenda;
    private String status;

    // Getters e Setters
    public int getIdEncomenda() { return idEncomenda; }
    public void setIdEncomenda(int id) { this.idEncomenda = id; }

    public String getCodigoEncomenda() { return codigoEncomenda; }
    public void setCodigoEncomenda(String codigo) { this.codigoEncomenda = codigo; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}