package com.example.cartService.dto;

public class ItemDTO {
    private String codigo;
    private String designacao;
    private double preco;
    private int nraquisicoes;
    private String estilo;
    private String cor;    
    private String tamanho;
    private String genero;
    private String disponibilidade;
    private String imagem;

    public ItemDTO() {
    }

    public ItemDTO(String codigo, 
                   String designacao, 
                   double preco, 
                   int nraquisicoes, 
                   String estilo, 
                   String cor, 
                   String tamanho, 
                   String genero, 
                   String disponibilidade, 
                   String imagem) {
        this.codigo = codigo;
        this.designacao = designacao;
        this.preco = preco;
        this.nraquisicoes = nraquisicoes;
        this.estilo = estilo;
        this.cor = cor;
        this.tamanho = tamanho;
        this.genero = genero;
        this.disponibilidade = disponibilidade;
        this.imagem = imagem;
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

    public int getNraquisicoes() {
        return nraquisicoes;
    }

    public void setNraquisicoes(int nraquisicoes) {
        this.nraquisicoes = nraquisicoes;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
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
}
