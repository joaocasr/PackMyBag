package com.exemplo.encomendaService.dto;

public class ItemDTO {
    
    private String codigo;
    private int nrPedido;
    private int idloja;
    public ItemDTO() {
    }

    public ItemDTO(String codigo, int nrPedido, int idloja) {
        this.codigo = codigo;
        this.nrPedido = nrPedido;
        this.idloja = idloja;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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
                ", nraquisicoes=" + nrPedido +
                ", idloja='" + idloja +
                '}';

    }

}
