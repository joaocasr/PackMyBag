package com.exemplo.encomendaService.dto;


public class EncomendaStatusDTO {
    private String codigoEncomenda;
    private String status;
    private String username;

    // Getters e Setters
    public String getCodigoEncomenda() { return codigoEncomenda; }
    public void setCodigoEncomenda(String codigo) { this.codigoEncomenda = codigo; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    @Override
    public String toString() {
        return "EncomendaStatusDTO,"+ codigoEncomenda + "," + status + "," + username;
    }
}