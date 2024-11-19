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
@Table(name="Pagamento")
public class Pagamento implements Serializable {
	public Pagamento() {
		this.itemEncomendas = new HashSet<>();
	}
	
	@Column(name="IDPagamento", nullable=false, length=10)	
	@Id	
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="CARTSERVICE_PAGAMENTO_IDPAGAMENTO_GENERATOR")	
	@SequenceGenerator(name="CARTSERVICE_PAGAMENTO_IDPAGAMENTO_GENERATOR", sequenceName ="CARTSERVICE_PAGAMENTO_IDPAGAMENTO_SEQ")	
	private int IDPagamento;
	
	@Column(name="Total", nullable=false)	
	private double total;
	
	@Column(name="LocalEntrega", nullable=true, length=255)	
	private String localEntrega;
	
	@Column(name="InicioAluguer", nullable=true, length=255)	
	private String inicioAluguer;
	
	@Column(name="FimAluguer", nullable=true, length=255)	
	private String fimAluguer;
	
	@Column(name="Codigo", nullable=true, length=255)	
	private String codigo;
	
	@Column(name="ModoPagamento", nullable=true, length=255)	
	private String modoPagamento;
	
	@Column(name="DataGeracao", nullable=true, length=255)	
	private String dataGeracao;

	@Column(name="Status", nullable=true, length=255)	
	private String status;

	@OneToMany(orphanRemoval=true, targetEntity=ItemEncomenda.class)
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.ALL})
	@JoinColumns({ @JoinColumn(name="PagamentoIDPagamento", nullable=false) })
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)
	private java.util.Set<ItemEncomenda> itemEncomendas = new java.util.HashSet();

	public void addItemEncomenda(ItemEncomenda i){
		this.itemEncomendas.add(i);
	}

	public java.util.Set<ItemEncomenda> getitens(){return this.itemEncomendas;}

	private void setIDPagamento(int value) {
		this.IDPagamento = value;
	}
	
	public int getIDPagamento() {
		return IDPagamento;
	}
	
	public int getORMID() {
		return getIDPagamento();
	}
	
	public void setTotal(double value) {
		this.total = value;
	}
	
	public double getTotal() {
		return total;
	}

	public void setLocalEntrega(String value) {
		this.localEntrega = value;
	}
	
	public String getLocalEntrega() {
		return localEntrega;
	}
	
	public void setInicioAluguer(String value) {
		this.inicioAluguer = value;
	}
	
	public String getInicioAluguer() {
		return inicioAluguer;
	}
	
	public void setFimAluguer(String value) {
		this.fimAluguer = value;
	}
	
	public String getFimAluguer() {
		return fimAluguer;
	}
	
	public void setCodigo(String value) {
		this.codigo = value;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setModoPagamento(String value) {
		this.modoPagamento = value;
	}
	
	public String getModoPagamento() {
		return modoPagamento;
	}

	public void setdataGeracao(String data) {
		this.dataGeracao = data;
	}
	
	public String getdataGeracao() {
		return dataGeracao;
	}
	
	public void setStatus(String value) {
		this.status = value;
	}
	
	public String getStatus() {
		return status;
	}
	
	public String toString() {
		return String.valueOf(getIDPagamento());
	}
	
}
