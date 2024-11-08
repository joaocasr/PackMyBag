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
@Table(name="Recomendacao")
public class Recomendacao implements Serializable {
	public Recomendacao() {
	}
	
	@Column(name="IDRecomendacao", nullable=false, length=10)	
	@Id	
	@GeneratedValue(generator="RECOMENDACOESSERVICE_RECOMENDACAO_IDRECOMENDACAO_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="RECOMENDACOESSERVICE_RECOMENDACAO_IDRECOMENDACAO_GENERATOR", strategy="native")	
	private int IDRecomendacao;
	
	@ManyToOne(targetEntity=Estilista.class, fetch=FetchType.LAZY)
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})
	@JoinColumns(value={ @JoinColumn(name="EstilistaIDEstilista", referencedColumnName="IDEstilista", nullable=false) }, foreignKey=@ForeignKey(name="FKRecomendac847827"))	
	private Estilista estilista;
	
	@ManyToOne(targetEntity=Cliente.class, fetch=FetchType.LAZY)
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns(value={ @JoinColumn(name="ClienteIDCliente", referencedColumnName="IDCliente", nullable=false) }, foreignKey=@ForeignKey(name="FKRecomendac802409"))	
	private Cliente cliente;
	
	@Column(name="Descricao", nullable=true, length=255)	
	private String descricao;

	@Column(name="Status", nullable=true, length=255)
	private String status; // 'completed' or 'pending'
	
	@OneToMany(targetEntity=Item.class)
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.ALL})
	@JoinColumns({ @JoinColumn(name="RecomendacaoIDRecomendacao", nullable=false) })	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set<Item> conjunto = new java.util.HashSet();
	
	public void setIDRecomendacao(int value) {
		this.IDRecomendacao = value;
	}
	
	public int getIDRecomendacao() {
		return IDRecomendacao;
	}
	
	public int getORMID() {
		return getIDRecomendacao();
	}


	public String getStatus(){
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setDescricao(String value) {
		this.descricao = value;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setCliente(Cliente value) {
		this.cliente = value;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setConjunto(java.util.Set<Item> value) {
		this.conjunto = value;
	}
	
	public java.util.Set<Item> getConjunto() {
		return this.conjunto;
	}
	
	public void addItem(Item i){
		this.conjunto.add(i);
	}

	public void removeItem(Item i){
		this.conjunto.remove(i);
	}

	public void setEstilista(Estilista value) {
		this.estilista = value;
	}
	
	public Estilista getEstilista() {
		return estilista;
	}
	
	public String toString() {
		return String.valueOf(getIDRecomendacao());
	}
	
}
