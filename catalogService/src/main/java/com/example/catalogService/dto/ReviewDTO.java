package com.example.catalogService.dto;

public class ReviewDTO {
    private int classificacao;
    private String nome;
    private String descricao;
    private String profileImg;
    private String timestamp;

    public ReviewDTO(int classificacao, String nome, String descricao, String profileImg, String timestamp) {
        this.classificacao = classificacao;
        this.nome = nome;
        this.descricao = descricao;
        this.profileImg = profileImg;
        this.timestamp = timestamp;
    }

    public int getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(int classificacao) {
        this.classificacao = classificacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}

