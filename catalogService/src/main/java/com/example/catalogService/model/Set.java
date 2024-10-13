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
package com.example.catalogService.model;

import java.io.Serializable;
import jakarta.persistence.*;
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="`Set`")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorValue("Set")
@PrimaryKeyJoinColumn(name="ItemIDItem", referencedColumnName="IDItem")
public class Set extends Item implements Serializable {
	public Set() {
	}
	
	@Column(name="NrPecas", nullable=false, length=10)	
	private int nrPecas;
	
	@ManyToMany(mappedBy="sets", targetEntity=Peca.class)
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set pecas = new java.util.HashSet();
	
	public void setNrPecas(int value) {
		this.nrPecas = value;
	}
	
	public int getNrPecas() {
		return nrPecas;
	}
	
	public void setPecas(java.util.Set value) {
		this.pecas = value;
	}
	
	public java.util.Set getPecas() {
		return pecas;
	}
	
	
	public String toString() {
		return super.toString();
	}
	
}
