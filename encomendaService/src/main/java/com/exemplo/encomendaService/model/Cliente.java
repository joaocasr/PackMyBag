package com.exemplo.encomendaService.model;

import java.io.Serializable;
//import javax.persistence.*;
import jakarta.persistence.*;

@Entity
//@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="Cliente")
public class Cliente implements Serializable {
	public Cliente() {
	}
	
	@Column(name="IDCliente", nullable=false, length=10)	
	@Id	
	@GeneratedValue(generator="ENCOMENDASERVICE_CLIENTE_IDCLIENTE_GENERATOR")	
	//@org.hibernate.annotations.GenericGenerator(name="ENCOMENDASERVICE_CLIENTE_IDCLIENTE_GENERATOR", strategy="native")
	@SequenceGenerator(name="ENCOMENDASERVICE_CLIENTE_IDCLIENTE_GENERATOR",sequenceName = "ENCOMENDASERVICE_CLIENTE_IDCLIENTE_SEQ")

	private int IDCliente;
	
	@Column(name="Nome", nullable=true, length=255)	
	private String nome;
	
	@Column(name="Username", nullable=true, length=255)	
	private String username;
	
	@Column(name="Email", nullable=true, length=255)	
	private String email;
	
	public Cliente(String nome, String email, String username) {
		this.nome = nome;
		this.email = email;
		this.username = username;
	}
	
	private void setIDCliente(int value) {
		this.IDCliente = value;
	}
	
	public int getIDCliente() {
		return IDCliente;
	}
	
	public int getORMID() {
		return getIDCliente();
	}
	
	public void setNome(String value) {
		this.nome = value;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setUsername(String value) {
		this.username = value;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setEmail(String value) {
		this.email = value;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String toString() {
		return String.valueOf(getIDCliente());
	}
	
}


// Acho que se deve atualizar o cliente para ele ter uma lista de encomendas