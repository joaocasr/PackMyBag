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
@Table(name="Review")
public class Review implements Serializable {
	public Review() {
	}
	
	@Column(name="IdReview", nullable=false, length=10)	
	@Id	
	@GeneratedValue(generator="CATALOGOSERVICE_REVIEW_IDREVIEW_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="CATALOGOSERVICE_REVIEW_IDREVIEW_GENERATOR", strategy="native")	
	private int idReview;
	
	@ManyToOne(targetEntity=Cliente.class, fetch=FetchType.LAZY)
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK, org.hibernate.annotations.CascadeType.DELETE})	
	@JoinColumns(value={ @JoinColumn(name="ClienteIDCliente", referencedColumnName="IDCliente", nullable=false) }, foreignKey=@ForeignKey(name="FKReview21175"))	
	private Cliente autor;
	
	@Column(name="Classificacao", nullable=false, length=10)	
	private int classificacao;
	
	@Column(name="Descricao", nullable=true, length=255)	
	private String descricao;
	
	@Column(name="Data", nullable=true, length=255)	
	private String data;
	
	private void setIdReview(int value) {
		this.idReview = value;
	}
	
	public int getIdReview() {
		return idReview;
	}
	
	public int getORMID() {
		return getIdReview();
	}
	
	public void setClassificacao(int value) {
		this.classificacao = value;
	}
	
	public int getClassificacao() {
		return classificacao;
	}
	
	public void setDescricao(String value) {
		this.descricao = value;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setData(String value) {
		this.data = value;
	}
	
	public String getData() {
		return data;
	}
	
	public void setAutor(Cliente value) {
		this.autor = value;
	}
	
	public Cliente getAutor() {
		return autor;
	}
	
	public String toString() {
		return String.valueOf(getIdReview());
	}
	
}
