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
import java.util.HashSet;

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

	@Column(name="Tamanho", nullable=true, length=255)
	private String tamanho;

	@ManyToMany(mappedBy="sets", targetEntity=Peca.class)
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set pecas = new java.util.HashSet();

	public Set(int nrpecas, java.util.Set pecas,
			   Loja loja, String codigo, String designacao, double preco, int nraquisicoes, String estilo, String cor, String tamanho, String tipo, String disponibilidade, String imagem){
		super(loja, codigo, designacao, preco, nraquisicoes, estilo, cor, tipo, disponibilidade, imagem,1);
		this.nrPecas = nrpecas;
		this.tamanho = tamanho;
		this.pecas = new HashSet(pecas);
	}

	public void setNrPecas(int value) {
		this.nrPecas = value;
	}
	
	public int getNrPecas() {
		return nrPecas;
	}

	public void setTamanho(String value) {
		this.tamanho = value;
	}

	public String getTamanho() {
		return tamanho;
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
