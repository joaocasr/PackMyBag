package com.example.recomendacoesservice.dto;

public class statusPedidoDTO {
    private String nome;
    private String status;

    public statusPedidoDTO(String nome, String status) {
        this.nome = nome == null ? "" : nome;
        this.status = status == null ? "" : status;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
