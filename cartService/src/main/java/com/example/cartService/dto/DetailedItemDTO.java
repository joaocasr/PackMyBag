package com.example.cartService.dto;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DetailedItemDTO {

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

    public DetailedItemDTO() {
    }

    public DetailedItemDTO(double preco, String designacao,String imagemUrl, int idloja, int nrPedido, String codigo) {
        this.preco = preco;
        this.designacao = designacao;
        this.imagemUrl = imagemUrl;
        this.idloja = idloja;
        this.nrPedido = nrPedido;
        this.codigo = codigo;
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

    public void getDesignacao(String designacao) {
        this.designacao = designacao;
    }

    public int getNrPedido() {
        return nrPedido;
    }

    public void setNrPedido(int nrPedido) {
        this.nrPedido = nrPedido;
    }

    public int getIdloja() {
        return idloja;
    }

    public void setIdloja(int idloja) {
        this.idloja = idloja;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
