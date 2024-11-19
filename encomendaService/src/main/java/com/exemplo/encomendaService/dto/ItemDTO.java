package com.exemplo.encomendaService.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ItemDTO {

    @JsonProperty("codigo")
    private String codigo;
    @JsonProperty("designacao")
    private String designacao;
    @JsonProperty("nrPedido")
    private int nrPedido;
    @JsonProperty("idloja")
    private int idloja;
    @JsonProperty("imagemUrl")
    private String imagemUrl;
    @JsonProperty("preco")
    private double preco;

    public ItemDTO() {
    }

    public ItemDTO(String codigo, String designacao,int nrPedido, int idloja,double preco,String imagem) {
        this.codigo = codigo;
        this.designacao = designacao;
        this.nrPedido = nrPedido;
        this.preco = preco;
        this.imagemUrl = imagem;
        this.idloja = idloja;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }


    public String getImagem() {
        return imagemUrl;
    }

    public void setImagem(String imagem) {
        this.imagemUrl = imagem;
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


    public void setNrPedido(int value) {
        this.nrPedido = value;
    }

    public int getNrPedido() {
        return nrPedido;
    }


    public void setIdLoja(int value) {
        this.idloja = value;
    }

    public int getIdLoja() {
        return idloja;
    }

    @Override
    public String toString() {
        return "ItemDTO{" +
                "codigo='" + codigo + '\'' +
                ", designacao='" + designacao + '\'' +
                ", nrPedido=" + nrPedido +
                ", idloja=" + idloja +
                ", imagemUrl='" + imagemUrl + '\'' +
                ", preco=" + preco +
                '}';
    }
}
