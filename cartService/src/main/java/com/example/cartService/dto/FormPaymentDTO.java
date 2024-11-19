package com.example.cartService.dto;

public class FormPaymentDTO {
    private String username;
    private String codigo;
    private double total;
    private String modoPagamento;
    private String status;
    private String dataGeracao;

    public FormPaymentDTO() {
    }

    public FormPaymentDTO(String username, String codigo, double total, String modoPagamento, String status, String dataGeracao) {
        this.username = username;
        this.codigo = codigo;
        this.total = total;
        this.modoPagamento = modoPagamento;
        this.status = status;
        this.dataGeracao = dataGeracao;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getModoPagamento() {
        return modoPagamento;
    }

    public void setModoPagamento(String modoPagamento) {
        this.modoPagamento = modoPagamento;
    }

    public String getDataGeracao() {
        return dataGeracao;
    }

    public void setDataGeracao(String dataGeracao) {
        this.dataGeracao = dataGeracao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
