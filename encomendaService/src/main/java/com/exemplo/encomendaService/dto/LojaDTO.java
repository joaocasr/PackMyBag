package com.exemplo.encomendaService.dto;

public class LojaDTO {

    private int IDLoja;
    private String nome;
    private String marca;
    private String localizacao;

    public LojaDTO() {
    }

    public LojaDTO(int IDLoja, String nome, String marca, String localizacao) {
        this.IDLoja = IDLoja;
        this.nome = nome;
        this.marca = marca;
        this.localizacao = localizacao;
    }

    public int getIDLoja() {
        return IDLoja;
    }

    public void setIDLoja(int IDLoja) {
        this.IDLoja = IDLoja;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    @Override
    public String toString() {
        return "LojaDTO{" +
                "IDLoja=" + IDLoja +
                ", nome='" + nome + '\'' +
                ", marca='" + marca + '\'' +
                ", localizacao='" + localizacao + '\'' +
                '}';
    }
    
}
