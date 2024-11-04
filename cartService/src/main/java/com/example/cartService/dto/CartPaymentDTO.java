package com.example.cartService.dto;

public class CartPaymentDTO {
    private String username;
    private String localEntrega;
    private String inicioAluguer;
    private String fimAluguer;
    private String modoPagamento;
    private String status;

    // Constructor
    public CartPaymentDTO(String username, String localEntrega, String inicioAluguer, String fimAluguer, String modoPagamento, String status    ) {
        this.username = username;
        this.localEntrega = localEntrega;
        this.inicioAluguer = inicioAluguer;
        this.fimAluguer = fimAluguer;
        this.modoPagamento = modoPagamento;
        this.status = status;
    }

    // Getters and Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getLocalEntrega() { return localEntrega; }
    public void setLocalEntrega(String localEntrega) { this.localEntrega = localEntrega; }
    
    public String getInicioAluguer() { return inicioAluguer; }
    public void setInicioAluguer(String inicioAluguer) { this.inicioAluguer = inicioAluguer; }
    
    public String getFimAluguer() { return fimAluguer; }
    public void setFimAluguer(String fimAluguer) { this.fimAluguer = fimAluguer; }
    
    public String getModoPagamento() { return modoPagamento; }
    public void setModoPagamento(String modoPagamento) { this.modoPagamento = modoPagamento; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
