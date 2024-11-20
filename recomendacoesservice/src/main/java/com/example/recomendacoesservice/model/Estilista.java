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
import java.util.HashSet;

import jakarta.persistence.*;

@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="Estilista")
public class Estilista implements Serializable {

	
	
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
	private java.util.Set<Pedido> pedidos;
	
	public Estilista(String username) {
		this.pedidos = new HashSet<>();
		this.username = username;
	}

	public Estilista() {

	}

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
