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
package com.example.cartService.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="CARTSERVICE_CLIENTE_IDCLIENTE_GENERATOR")	
	@SequenceGenerator(name="CARTSERVICE_CLIENTE_IDCLIENTE_GENERATOR", sequenceName ="CARTSERVICE_CLIENTE_IDCLIENTE_SEQ")	
	private int IDCliente;
	
	@ManyToOne(targetEntity=Cart.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK, org.hibernate.annotations.CascadeType.DELETE})	
	@JoinColumns(value={ @JoinColumn(name="CartIDCart", referencedColumnName="IDCart", nullable=false) }, foreignKey=@ForeignKey(name="FKCliente384303"))	
	private com.example.cartService.model.Cart cart;
	
	@Column(name="Nome", nullable=true, length=255)	
	private String nome;
	
	@Column(name="Username", nullable=true, length=255)	
	private String username;
	
	@Column(name="Email", nullable=true, length=255)	
	private String email;
	
	@OneToMany(targetEntity=com.example.cartService.model.Pagamento.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="ClienteIDCliente", nullable=false) })	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private Set<Pagamento> transacoes = new HashSet<Pagamento>();
	
	public Cliente(String nome,String username, String email){
		this.nome = nome;
		this.username = username;
		this.email= email;
		this.cart = null;
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
	
	public void setCart(Cart value) {
		this.cart = value;
	}
	
	public Cart getCart() {
		return cart;
	}
	
	public void setTransacoes(Set<Pagamento> value) {
		this.transacoes = value;
	}
	
	public Set<Pagamento> getTransacoes() {
		return transacoes;
	}
	
	
	public String toString() {
		return String.valueOf(getIDCliente());
	}
	
}
