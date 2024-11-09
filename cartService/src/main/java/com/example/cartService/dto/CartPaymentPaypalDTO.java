package com.example.cartService.dto;

import java.time.LocalDateTime;
import java.util.Set;

public class CartPaymentPaypalDTO {
    private String username;
    private String codigo;
    private String localEntrega;
    private LocalDateTime inicioAluguer;
    private LocalDateTime fimAluguer;
    private String modoPagamento;
    private String status;
    private String paypalOrderId;
    private Set<CartItemDTO> items;

    public CartPaymentPaypalDTO() {
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

    public String getLocalEntrega() {
        return localEntrega;
    }

    public void setLocalEntrega(String localEntrega) {
        this.localEntrega = localEntrega;
    }

    public LocalDateTime getInicioAluguer() {
        return inicioAluguer;
    }

    public void setInicioAluguer(LocalDateTime inicioAluguer) {
        this.inicioAluguer = inicioAluguer;
    }

    public LocalDateTime getFimAluguer() {
        return fimAluguer;
    }

    public void setFimAluguer(LocalDateTime fimAluguer) {
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

    public String getPaypalOrderId() {
        return paypalOrderId;
    }

    public void setPaypalOrderId(String paypalOrderId) {
        this.paypalOrderId = paypalOrderId;
    }

    public Set<CartItemDTO> getItems() {
        return items;
    }

    public void setItems(Set<CartItemDTO> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "CartPaymentPaypalDTO{" +
                "username='" + username + '\'' +
                ", codigo='" + codigo + '\'' +
                ", localEntrega='" + localEntrega + '\'' +
                ", inicioAluguer=" + inicioAluguer +
                ", fimAluguer=" + fimAluguer +
                ", modoPagamento='" + modoPagamento + '\'' +
                ", status='" + status + '\'' +
                ", paypalOrderId='" + paypalOrderId + '\'' +
                ", items=" + items +
                '}';
    }
}
