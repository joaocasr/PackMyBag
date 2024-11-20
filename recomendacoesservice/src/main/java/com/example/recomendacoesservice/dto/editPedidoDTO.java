package com.example.recomendacoesservice.dto;

import java.util.HashSet;
import java.util.Set;

public class editPedidoDTO {

    private String nome;
    private String descricao;
    private String status;


    public editPedidoDTO(String nome, String descricao, String status) {
        this.nome = nome;
        this.descricao = descricao == null ? "" : descricao;
        this.status = status == null ? "" : status;
    }

    public String getNome() {
        return this.nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDescricao() {
        return this.descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getStatus() {
        return this.status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
