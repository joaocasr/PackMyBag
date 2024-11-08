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
@Table(name="Cart")
public class Cart implements Serializable {
	public Cart() {
	}
	
	@Column(name="IDCart", nullable=false, length=10)	
	@Id	
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="CARTSERVICE_CART_IDCART_GENERATOR")	
	@SequenceGenerator(name="CARTSERVICE_CART_IDCART_GENERATOR", sequenceName ="CARTSERVICE_CART_IDCART_SEQ")	
	private int IDCart;
	
	@OneToMany(orphanRemoval=true, targetEntity=com.example.cartService.model.Item.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.ALL})	
	@JoinColumns({ @JoinColumn(name="CartIDCart", nullable=false) })	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private Set<Item> itens = new HashSet<Item>();
	
	private void setIDCart(int value) {
		this.IDCart = value;
	}
	
	public int getIDCart() {
		return IDCart;
	}
	
	public int getORMID() {
		return getIDCart();
	}
	
	public void setItens(Set<Item> value) {
		this.itens = value;
	}
	
	public Set<Item> getItens() {
		return itens;
	}
	
	public void removeItem(String codigo, int idloja){
		this.itens.removeIf(cartItem -> cartItem.getCodigo().equals(codigo) && cartItem.getIdLoja()==idloja);
	}
	
	public String toString() {
		return String.valueOf(getIDCart());
	}
	
}
