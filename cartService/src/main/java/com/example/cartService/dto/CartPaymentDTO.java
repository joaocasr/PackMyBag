package com.example.cartService.dto;

import java.util.List;

public class CartPaymentDTO {
    private String username;
    private String codigo;
    private String localEntrega;
    private String inicioAluguer;
    private String fimAluguer;
    private double total;
    private String modoPagamento;
    private String status;
    private String dataGeracao;
    //criar a lista do item
    private List<ItemDTO> items;

    // Constructor

    public CartPaymentDTO(String username, String codigo, String localEntrega, String inicioAluguer, String fimAluguer, double total, String modoPagamento, String status,String dataGeracao, List<ItemDTO> items) {
        this.username = username;
        this.codigo = codigo;
        this.localEntrega = localEntrega;
        this.inicioAluguer = inicioAluguer;
        this.fimAluguer = fimAluguer;
        this.total = total;
        this.modoPagamento = modoPagamento;
        this.dataGeracao = dataGeracao;
        this.status = status;
        this.items = items;
    }

    public CartPaymentDTO() {

    }

    // Getters and Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    
    public String getLocalEntrega() { return localEntrega; }
    public void setLocalEntrega(String localEntrega) { this.localEntrega = localEntrega; }
    
    public String getInicioAluguer() { return inicioAluguer; }
    public void setInicioAluguer(String inicioAluguer) { this.inicioAluguer = inicioAluguer; }
    
    public String getFimAluguer() { return fimAluguer; }
    public void setFimAluguer(String fimAluguer) { this.fimAluguer = fimAluguer; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    public String getModoPagamento() { return modoPagamento; }
    public void setModoPagamento(String modoPagamento) { this.modoPagamento = modoPagamento; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getDataGeracao() { return dataGeracao; }
    public void setDataGeracao(String data) { this.dataGeracao = data; }

    public List<ItemDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemDTO> items) {
        this.items = items;
    }
}
