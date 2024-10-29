/**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

/**
 * Licensee: Afonso Marques(University of Minho)
 * License Type: Academic
 */
package com.example.cartService.model;

import java.io.Serializable;
import jakarta.persistence.*;
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="Cart")
public class Cart implements Serializable {
	public Cart() {
	}
	
	@Column(name="IDCart", nullable=false, length=10)	
	@Id	
	@GeneratedValue(generator="CARTSERVICE_CART_IDCART_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="CARTSERVICE_CART_IDCART_GENERATOR", strategy="native")	
	private int IDCart;
	
	@OneToMany(orphanRemoval=true, targetEntity=com.example.cartService.model.Item.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.ALL})	
	@JoinColumns({ @JoinColumn(name="CartIDCart", nullable=false) })	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.List<Item> itens = new java.util.ArrayList<>();
	
	private void setIDCart(int value) {
		this.IDCart = value;
	}
	
	public int getIDCart() {
		return IDCart;
	}
	
	public int getORMID() {
		return getIDCart();
	}
	
	public void setItens(java.util.List<Item> value) {
		this.itens = value;
	}
	
	public java.util.List<Item> getItens() {
		return itens;
	}
	
	
	public String toString() {
		return String.valueOf(getIDCart());
	}
	
}
