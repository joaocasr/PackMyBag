package com.example.cartService.dto;

public class PagamentoDTO {
    private String codigo;
    private double total;
    private String localEntrega;
    private String inicioAluguer;
    private String fimAluguer;
    private String modoPagamento;
    private String status;

    public PagamentoDTO(String codigo, double total, String localEntrega, 
                       String inicioAluguer, String fimAluguer, 
                       String modoPagamento, String status) {
        this.codigo = codigo;
        this.total = total;
        this.localEntrega = localEntrega;
        this.inicioAluguer = inicioAluguer;
        this.fimAluguer = fimAluguer;
        this.modoPagamento = modoPagamento;
        this.status = status;
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

    public String getLocalEntrega() {
        return localEntrega;
    }

    public void setLocalEntrega(String localEntrega) {
        this.localEntrega = localEntrega;
    }

    public String getInicioAluguer() {
        return inicioAluguer;
    }

    public void setInicioAluguer(String inicioAluguer) {
        this.inicioAluguer = inicioAluguer;
    }

    public String getFimAluguer() {
        return fimAluguer;
    }

    public void setFimAluguer(String fimAluguer) {
        this.fimAluguer = fimAluguer;
    }

    public String getModoPagamento() {
        return modoPagamento;
    }

    public void setModoPagamento(String modoPagamento) {
        this.modoPagamento = modoPagamento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
