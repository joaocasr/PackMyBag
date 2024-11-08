package com.example.recomendacoesservice.dto;

import com.example.recomendacoesservice.model.Item;

public class itemDTO {
    private String codigo;
    private String designacao;
    private int idLoja;

    public itemDTO(String codigo, String designacao, int idLoja) {
        this.codigo = codigo;
        this.designacao = designacao;
        this.idLoja = idLoja;
    }

    public itemDTO(Item item) {
        this.codigo = item.getCodigo();
        this.designacao = item.getDesignacao();
        this.idLoja = item.getIdLoja();
    }

    public Item itemDTOtoItem() {
        Item i = new Item();
        i.setIdLoja(this.getIdLoja());
        i.setDesignacao(this.getDesignacao());
        i.setCodigo(this.getCodigo());

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
    public int getIdLoja() {
        return this.idLoja;
    }
    public void setIdLoja(int idLoja) {
        this.idLoja = idLoja;
    }
}
