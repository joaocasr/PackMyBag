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
import jakarta.persistence.*;
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="Cliente")
public class Cliente implements Serializable {
	public Cliente() {
	}
	
	@Column(name="IDCliente", nullable=false, length=10)	
	@Id	
	@GeneratedValue(generator="RECOMENDACOESSERVICE_CLIENTE_IDCLIENTE_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="RECOMENDACOESSERVICE_CLIENTE_IDCLIENTE_GENERATOR", strategy="native")	
	private int IDCliente;

	@Column(name="Username", nullable=true, length=255)
	private String username;

	@OneToMany(mappedBy="cliente", orphanRemoval=true, targetEntity=Recomendacao.class)
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.ALL})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set recomendacoes = new java.util.HashSet();
	
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
	
	public void setRecomendacoes(java.util.Set value) {
		this.recomendacoes = value;
	}
	
	public java.util.Set getRecomendacoes() {
		return recomendacoes;
	}

	public void addRecomendacao(Recomendacao r){
		this.recomendacoes.add(r);
	}

	public String toString() {
		return String.valueOf(getIDCliente());
	}
	
}
