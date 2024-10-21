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
@Table(name="Peca")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorValue("Peca")
@PrimaryKeyJoinColumn(name="ItemIDItem", referencedColumnName="IDItem")
public class Peca extends Item implements Serializable {
	public Peca() {
	}

	@Column(name="Tamanho", nullable=true, length=255)
	private String tamanho;

	@ManyToMany(targetEntity=Set.class)
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@JoinTable(name="Set_Peca", joinColumns={ @JoinColumn(name="PecaItemIDItem") }, inverseJoinColumns={ @JoinColumn(name="SetItemIDItem") })	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set<Set> sets = new java.util.HashSet();

	public Peca(Loja loja, String codigo, String designacao, double preco, int nraquisicoes, String estilo, String cor, String tamanho, String tipo, String disponibilidade, String imagem,int nrdisponiveis) {
		super(loja, codigo, designacao, preco, nraquisicoes, estilo, cor, tipo, disponibilidade, imagem,nrdisponiveis);
		this.tamanho = tamanho;
		this.sets = new HashSet();
	}

	public Peca(Loja loja, String codigo, String designacao, double preco, int nraquisicoes, String estilo, String cor, String tamanho, String tipo, String disponibilidade, String imagem,java.util.Set sets,int nrdisponiveis) {
		super(loja, codigo, designacao, preco, nraquisicoes, estilo, cor, tipo, disponibilidade, imagem,nrdisponiveis);
		this.tamanho = tamanho;
		this.sets = sets;
	}

	public void setTamanho(String value) {
		this.tamanho = value;
	}

	public String getTamanho() {
		return tamanho;
	}

	public void setSets(java.util.Set<Set> value) {
		this.sets = value;
	}
	
	public java.util.Set<Set> getSets() {
		return sets;
	}
	
	public void removeSet(String codigo){
		this.sets.removeIf(x->x.getCodigo().equals(codigo));
	}

	public String toString() {
		return super.toString();
	}
	
}
