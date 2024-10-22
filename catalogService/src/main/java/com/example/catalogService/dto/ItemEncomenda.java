package com.example.catalogService.dto;

public class ItemEncomenda {
    private String codigo;
    private int idloja;
    private int quantidade;

    public ItemEncomenda(String codigo, int idloja, int quantidade) {
        this.codigo = codigo;
        this.idloja = idloja;
        this.quantidade = quantidade;
    }

    public ItemEncomenda() {
    }

    public int getIdloja() {
        return idloja;
    }

    public void setIdloja(int idloja) {
        this.idloja = idloja;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
