package com.example.notificacoesService.dto;


public class EncomendaInsertDTO {

    private String codigoEncomenda;
    private String status;
    private int diasRestantes;
    private String usernameCliente;
    private String nomeCliente;
    private String emailCliente;

    // Construtor vazio
    public EncomendaInsertDTO() {}

    // Construtor com parâmetros


    public EncomendaInsertDTO(String codigoEncomenda, String status, int diasRestantes, String usernameCliente, String nomeCliente, String emailCliente) {
        this.codigoEncomenda = codigoEncomenda;
        this.status = status;
        this.diasRestantes = diasRestantes;
        this.usernameCliente = usernameCliente;
        this.nomeCliente = nomeCliente;
        this.emailCliente = emailCliente;
    }

    public String getCodigoEncomenda() {
        return codigoEncomenda;
    }

    public void setCodigoEncomenda(String CodigoEncomenda) {
        this.codigoEncomenda = CodigoEncomenda;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String Status) {
        this.status = Status;
    }

    public String getUsernameCliente() {
        return usernameCliente;
    }

    public void setUsernameCliente(String usernameCliente) {
        this.usernameCliente = usernameCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public int getDiasRestantes() {
        return diasRestantes;
    }

    public void setDiasRestantes(int diasRestantes) {
        this.diasRestantes = diasRestantes;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
// toString
    @Override
    public String toString() {
        return "EncomendaInsertDTO{" +
                "codigoEncomenda='" + codigoEncomenda + '\'' +
                ", status='" + status + '\'' +
                ", diasRestantes=" + diasRestantes +
                ", usernameCliente='" + usernameCliente + '\'' +
                ", nomeCliente='" + nomeCliente + '\'' +
                ", emailCliente='" + emailCliente + '\'' +
                '}';
    }
}
