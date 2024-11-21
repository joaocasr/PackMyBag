/**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

/**
 * Licensee: joao(Universidade do Minho)
 * License Type: Academic
 */
package com.example.recomendacoesservice.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="Cliente")
public class Cliente implements Serializable {
	public Cliente() {
	}
	
	@Column(name="IDCliente", nullable=false, length=10)	
	@Id	
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="RECOMENDACOESSERVICE_CLIENTE_IDCLIENTE_GENERATOR")
	@SequenceGenerator(name="RECOMENDACOESSERVICE_CLIENTE_IDCLIENTE_GENERATOR",sequenceName="RECOMENDACOESSERVICE_CLIENTE_IDCLIENTE_SEQ")
	private int IDCliente;

	@Column(name="Username", nullable=true, length=255)
	private String username;
	
	private void setIDCliente(int value) {
		this.IDCliente = value;
	}
	
	public int getIDCliente() {
		return IDCliente;
	}
	
	public int getORMID() {
		return getIDCliente();
	}
	
	public void setUsername(String value) {
		this.username = value;
	}
	
	public String getUsername() {
		return username;
	}

	public String toString() {
		return String.valueOf(getIDCliente());
	}
	
}
