package com.example.notificacoesService.dto;

public class ClientItemDTO {
    private String codigo;
    private String designacao;
    private String disponibilidade;
    private int IDLoja;
    private String nome;
    private String username;
    private String nomeuser;
    private String email;

    public ClientItemDTO() {
    }

    public ClientItemDTO(String codigo, String designacao, String disponibilidade, int IDLoja, String nome, String username, String nomeuser, String email) {
        this.codigo = codigo;
        this.designacao = designacao;
        this.disponibilidade = disponibilidade;
        this.IDLoja = IDLoja;
        this.nome = nome;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIDLoja() {
        return IDLoja;
    }

    public void setIDLoja(int IDLoja) {
        this.IDLoja = IDLoja;
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
