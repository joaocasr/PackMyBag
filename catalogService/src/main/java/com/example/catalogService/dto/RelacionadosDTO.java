package com.example.catalogService.dto;

public class RelacionadosDTO {
    private int itemID;
    private String designacao;
    private String imagem;

    public RelacionadosDTO(String designacao, String imagem, int itemID) {
        this.designacao = designacao;
        this.imagem = imagem;
        this.itemID = itemID;
    }

    public RelacionadosDTO() {
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
