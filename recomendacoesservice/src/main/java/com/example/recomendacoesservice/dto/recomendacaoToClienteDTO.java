package com.example.recomendacoesservice.dto;

import java.util.HashSet;
import java.util.Set;

public class recomendacaoToClienteDTO {
    private int IDCliente;
    private int IDEstilista;
    private String descricao;
    private Set<itemDTO> conjunto;
    private String status;

    public recomendacaoToClienteDTO(int IDCliente, int IDEstilista, String descricao, Set<itemDTO> conjunto, String status) {
        this.IDCliente = IDCliente;
        this.IDEstilista = IDEstilista;
        this.descricao = descricao == null ? "" : descricao;
        this.conjunto = conjunto == null ? new HashSet<>() : conjunto;
        this.status = status == null ? "pending" : status;
    }

    public int getIDCliente() {
        return this.IDCliente;
    }
    public void setIDCliente(int IDCliente) {
        this.IDCliente = IDCliente;
    }
    public int getIDEstilista() {
        return this.IDEstilista;
    }
    public void setIDEstilista(int IDEstilista) {
        this.IDEstilista = IDEstilista;
    }
    public String getDescricao() {
        return this.descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public Set<itemDTO> getConjunto() {
        return this.conjunto;
    }
    public void setConjunto(Set<itemDTO> conjunto) {
        this.conjunto = conjunto;
    }

    public String getStatus() {
        return this.status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
