package com.example.cartService.dto;

public class CartPaymentStatusChangeDTO {
    private String codigo;
    private String username;
    private String status;

    public CartPaymentStatusChangeDTO(String codigo, String username, String status) {
        this.codigo = codigo;
        this.username = username;
        this.status = status;
    }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getStatus() { return status; }
}
