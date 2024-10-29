package com.exemplo.encomendaService.dto;


public class ClienteDTO {
    
    private int IDCliente;
    private String nome;
    private String username;
    private String email;

    public ClienteDTO() {
    }

    public ClienteDTO(int IDCliente, String nome, String username, String email) {
        this.IDCliente = IDCliente;
        this.nome = nome;
        this.username = username;
        this.email = email;
    }

    public int getIDCliente() {
        return IDCliente;
    }

    public void setIDCliente(int IDCliente) {
        this.IDCliente = IDCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ClienteDTO{" +
                "IDCliente=" + IDCliente +
                ", nome='" + nome + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
