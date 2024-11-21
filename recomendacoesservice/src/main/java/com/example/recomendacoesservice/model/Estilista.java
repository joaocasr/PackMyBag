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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="Estilista")
public class Estilista implements Serializable {
	public Estilista() {
	}
	public Estilista(String username){
		this.username = username;
	}

	@Column(name="IDEstilista", nullable=false, length=10)	
	@Id	
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="RECOMENDACOESSERVICE_ESTILISTA_IDESTILISTA_GENERATOR")
	@SequenceGenerator(name="RECOMENDACOESSERVICE_ESTILISTA_IDESTILISTA_GENERATOR",sequenceName="RECOMENDACOESSERVICE_ESTILISTA_IDESTILISTA_SEQ")
	private int IDEstilista;
	
	@Column(name="Username", nullable=true, length=255)
	private String username;
	
	@OneToMany(targetEntity=Pedido.class)
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="EstilistaIDEstilista", nullable=false) })	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set pedidos = new java.util.HashSet();
	
	private void setIDEstilista(int value) {
		this.IDEstilista = value;
	}
	
	public int getIDEstilista() {
		return IDEstilista;
	}
	
	public int getORMID() {
		return getIDEstilista();
	}
	
	public void setUsername(String value) {
		this.username = value;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setPedidos(java.util.Set value) {
		this.pedidos = value;
	}

	public void addPedido(Pedido pedido){
		this.pedidos.add(pedido);
	}
	
	public java.util.Set getPedidos() {
		return pedidos;
	}
	
	public String toString() {
		return String.valueOf(getIDEstilista());
	}

}
