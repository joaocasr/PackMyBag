package com.example.catalogService.dto;


public class EditItemDTO {
    private String codigo;
    private String designacao;
    private double preco;
    private String estilo;
    private String cor;
    private String tipo;
    private String disponibilidade;
    private String imagem;
    private int nrDisponiveis;
    private int idLoja;
    public EditItemDTO() {
    }

    public EditItemDTO(String codigo, String designacao, double preco, String estilo, String tipo, String cor, String disponibilidade, String imagem, int nrDisponiveis, int idloja) {
        this.codigo = codigo;
        this.designacao = designacao;
        this.preco = preco;
        this.estilo = estilo;
        this.tipo = tipo;
        this.cor = cor;
        this.disponibilidade = disponibilidade;
        this.imagem = imagem;
        this.nrDisponiveis = nrDisponiveis;
        this.idLoja = idloja;
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

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(String disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public int getNrDisponiveis() {
        return nrDisponiveis;
    }

    public void setNrDisponiveis(int nrDisponiveis) {
        this.nrDisponiveis = nrDisponiveis;
    }

    public int getIdLoja() {
        return idLoja;
    }

    public void setIdLoja(int idLoja) {
        this.idLoja = idLoja;
    }
}
