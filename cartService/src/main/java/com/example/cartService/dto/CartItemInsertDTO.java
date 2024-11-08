package com.example.cartService.dto;

public class CartItemInsertDTO {
    private String codigo;
    private int idLoja;
    private String username;
    private String nome;
    private String email;
    private String designacao;
    private String imagem;
    private double preco;
    private int quantidade;

    public CartItemInsertDTO() {
    }

    public CartItemInsertDTO(String codigo, int loja, String username, String nome, String email, String designacao, String imagem, double preco, int quantidade) {
        this.codigo = codigo;
        this.idLoja = loja;
        this.username = username;
        this.nome = nome;
        this.email = email;
        this.designacao = designacao;
        this.imagem = imagem;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getIdLoja() {
        return idLoja;
    }

    public void setIdLoja(int idLoja) {
        this.idLoja = idLoja;
    }

    public String getNome(){
        return this.nome;
    }
    public String getEmail(){
        return this.email;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
