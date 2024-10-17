package com.example.catalogService.dto;

import java.util.HashSet;
import java.util.Set;

public class SetInsertDTO {
    private String codigo;
    private String designacao;
    private double preco;
    private String estilo;
    private String tamanho;
    private String tipo;
    private String disponibilidade;
    private String imagem;
    private int idLoja;
    private Set<String> codigoPecas;

    public SetInsertDTO(){
        this.codigoPecas = new HashSet<>();
    }

    public SetInsertDTO(String codigo, String designacao, double preco, String estilo, String tamanho, String tipo, String disponibilidade, String imagem, int idloja, Set<String> pecas) {
        this.codigo = codigo;
        this.designacao = designacao;
        this.preco = preco;
        this.estilo = estilo;
        this.tamanho = tamanho;
        this.tipo = tipo;
        this.disponibilidade = disponibilidade;
        this.imagem = imagem;
        this.idLoja = idloja;
        this.codigoPecas = new HashSet<>(pecas);
    }

    public Set<String> getCodigoPecas() {
        return codigoPecas;
    }

    public void setCodigoPecas(Set<String> codigoPecas) {
        this.codigoPecas = codigoPecas;
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

    public int getIdLoja() {
        return idLoja;
    }

    public void setIdLoja(int idLoja) {
        this.idLoja = idLoja;
    }

}
