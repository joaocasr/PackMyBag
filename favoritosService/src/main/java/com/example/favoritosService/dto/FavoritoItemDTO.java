package com.example.favoritosService.dto;

public class FavoritoItemDTO {
    private String codigo;
    private String designacao;
    private double preco;
    private String imagem;
    private String tipo;
    private String disponibilidade;
    private int identificador;
    private int idloja;

    public FavoritoItemDTO(String codigo,String designacao, double preco, String imagem,String tipo,String disponibilidade, int identificacor, int idloja) {
        this.codigo = codigo;
        this.designacao = designacao;
        this.preco = preco;
        this.imagem = imagem;
        this.tipo = tipo;
        this.disponibilidade = disponibilidade;
        this.identificador = identificacor;
        this.idloja = idloja;
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

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(String disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public int getIdloja() {
        return idloja;
    }

    public void setIdloja(int idloja) {
        this.idloja = idloja;
    }
}
