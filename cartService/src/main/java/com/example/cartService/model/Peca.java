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
@Table(name="Peca")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorValue("Peca")
@PrimaryKeyJoinColumn(name="ItemIDItem", referencedColumnName="IDItem")
public class Peca extends com.example.cartService.model.Item implements Serializable {
	public Peca() {
	}
	
	@ManyToMany(targetEntity=com.example.cartService.model.Set.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@JoinTable(name="Set_Peca", joinColumns={ @JoinColumn(name="PecaItemIDItem") }, inverseJoinColumns={ @JoinColumn(name="SetItemIDItem") })	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set<Set> sets = new java.util.HashSet();
	
	public void setSets(java.util.Set<Set> value) {
		this.sets = value;
	}
	
	public java.util.Set<Set> getSets() {
		return sets;
	}
	
	
	public String toString() {
		return super.toString();
	}
	
}
