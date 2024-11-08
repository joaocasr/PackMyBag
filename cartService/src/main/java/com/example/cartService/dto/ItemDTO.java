package com.example.cartService.dto;

public class ItemDTO {
    private String codigo;
    private int idloja;
    private int quantidade;

    public ItemDTO() {
    }

    public ItemDTO(String codigo, 
                   int idloja,
                   int quantidade) {
        this.codigo = codigo;
        this.idloja = idloja;
        this.quantidade = quantidade;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getIdloja() {
        return idloja;
    }

    public void setIdloja(int idloja) {
        this.idloja = idloja;
    }


    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
