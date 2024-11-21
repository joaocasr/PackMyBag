package com.example.recomendacoesservice.dto;

import com.example.recomendacoesservice.model.Item;

public class itemDTO {
    private String codigo;
    private String designacao;
    private String imagem;
    private double preco;
    private int identificador;
    private int idLoja;

    public itemDTO(String codigo, String designacao, String imagem, double preco, int identificador, int idLoja) {
        this.codigo = codigo;
        this.designacao = designacao;
        this.imagem = imagem;
        this.preco = preco;
        this.identificador = identificador;
        this.idLoja = idLoja;
    }

    public itemDTO(Item item) {
        this.codigo = item.getCodigo();
        this.designacao = item.getDesignacao();
        this.imagem = item.getImagem();
        this.preco = item.getPreco();
        this.identificador = item.getIdentificador();
        this.idLoja = item.getIdLoja();
    }

    public Item itemDTOtoItem() {
        Item i = new Item();
        i.setIdLoja(this.getIdLoja());
        i.setDesignacao(this.getDesignacao());
        i.setCodigo(this.getCodigo());
        i.setImagem(this.getImagem());
        i.setPreco(this.getPreco());
        i.setIdentificador(this.getIdentificador());

        return i;
    }

    public String getCodigo() {
        return this.codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getDesignacao() {
        return this.designacao;
    }
    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }
    public String getImagem() {
        return this.imagem;
    }
    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
    public double getPreco() {
        return this.preco;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }
    public int getIdentificador() {
        return this.identificador;
    }
    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }
    public int getIdLoja() {
        return this.idLoja;
    }
    public void setIdLoja(int idLoja) {
        this.idLoja = idLoja;
    }
}
