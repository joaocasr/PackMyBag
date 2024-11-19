package com.exemplo.encomendaService.dto;

public class ItemDTO {
    
    private String codigo;
    private String designacao;
    private double preco;
    private int nrPedido;
    private int idloja;
    public ItemDTO() {
    }

    public ItemDTO(String codigo, String designacao, double preco, int nrPedido, int idloja) {
        this.codigo = codigo;
        this.designacao = designacao;
        this.preco = preco;
        this.nrPedido = nrPedido;
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
                ", preco=" + preco +
                ", nraquisicoes=" + nrPedido +
                ", idloja='" + idloja +
                '}';

    }

}
