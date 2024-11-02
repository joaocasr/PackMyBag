package com.example.notificacoesService.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClientItemDTO {
    private String codigo;
    private String designacao;
    private String disponibilidade;
    @JsonProperty("idLoja")
    private String idLoja;
    private String username;
    private String nomeuser;
    private String email;

    public ClientItemDTO() {
    }

    public ClientItemDTO(String codigo, String designacao, String disponibilidade, String idLoja, String username, String nomeuser, String email) {
        this.codigo = codigo;
        this.designacao = designacao;
        this.disponibilidade = disponibilidade;
        this.idLoja = idLoja;
        this.username = username;
        this.nomeuser = nomeuser;
        this.email = email;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    public String getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(String disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public String getIDLoja() {
        return idLoja;
    }

    public void setIDLoja(String IDLoja) {
        this.idLoja = IDLoja;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomeuser() {
        return nomeuser;
    }

    public void setNomeuser(String nomeuser) {
        this.nomeuser = nomeuser;
    }
}
