package com.example.catalogService.dto;

import java.util.Set;

public class PecaInsertDTO {
    private String codigo;
    private String designacao;
    private double preco;
    private String estilo;
    private Set<String> cores;
    private String tamanho;
    private String tipo;
    private String disponibilidade;
    private String imagem;
    private int idLoja;
    private int nrdisponiveis;

    public PecaInsertDTO(String codigo, String designacao, double preco, String estilo, Set<String> cores, String tamanho, String tipo, String disponibilidade, String imagem, int idloja,int nrdisponiveis) {
        this.codigo = codigo;
        this.designacao = designacao;
        this.preco = preco;
        this.estilo = estilo;
        this.cores = cores;
        this.tamanho = tamanho;
        this.tipo = tipo;
        this.disponibilidade = disponibilidade;
        this.imagem = imagem;
        this.idLoja = idloja;
        this.nrdisponiveis = nrdisponiveis;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
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

    public Set<String> getCores() {
        return cores;
    }

    public void setCores(Set<String> cores) {
        this.cores = cores;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(String disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


    public int getNrdisponiveis() {
        return nrdisponiveis;
    }

    public void setNrdisponiveis(int nrdisponiveis) {
        this.nrdisponiveis = nrdisponiveis;
    }

    public int getIdLoja() {
        return idLoja;
    }

    public void setIdLoja(int idLoja) {
        this.idLoja = idLoja;
    }
}
