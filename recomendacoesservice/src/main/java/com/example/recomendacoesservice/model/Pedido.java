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
@Table(name="Pedido")
public class Pedido implements Serializable {
	public Pedido() {
	}
	
	@Column(name="IDPedido", nullable=false, length=10)	
	@Id	
	@GeneratedValue(generator="RECOMENDACOESSERVICE_PEDIDO_IDPEDIDO_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="RECOMENDACOESSERVICE_PEDIDO_IDPEDIDO_GENERATOR", strategy="native")	
	private int IDPedido;
	
	@ManyToOne(targetEntity=Cliente.class, fetch=FetchType.LAZY)
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK, org.hibernate.annotations.CascadeType.DELETE})	
	@JoinColumns(value={ @JoinColumn(name="ClienteIDCliente", referencedColumnName="IDCliente", nullable=false) }, foreignKey=@ForeignKey(name="FKPedido815811"))	
	private Cliente cliente;
	
	@Column(name="Estilos", nullable=true, length=255)	
	private String estilos;
	
	@Column(name="Cores", nullable=true, length=255)	
	private String cores;
	
	@Column(name="NrOutfits", nullable=false, length=10)
	private int nrOutfits;
	
	@Column(name="Orcamento", nullable=false)	
	private double orcamento;

	@Column(name="PeçasExcluidas", nullable=true, length=255)
	private String peçasExcluidas;

	@Column(name="FabricsPreferences", nullable=true, length=255)
	private String fabricsPreferences;

	@Column(name="Occasions", nullable=true, length=255)
	private String occasions;
	
	private void setIDPedido(int value) {
		this.IDPedido = value;
	}
	
	public int getIDPedido() {
		return this.IDPedido;
	}
	
	public int getORMID() {
		return getIDPedido();
	}
	
	public void setEstilos(String value) {
		this.estilos = value;
	}
	
	public String getEstilos() {
		return this.estilos;
	}
	
	public void setCores(String value) {
		this.cores = value;
	}
	
	public String getCores() {
		return this.cores;
	}
	
	public void setNrOutfits(int value) {
		this.nrOutfits = value;
	}
	
	public int getNrOutfits() {
		return this.nrOutfits;
	}
	
	public void setOrcamento(double value) {
		this.orcamento = value;
	}
	
	public double getOrcamento() {
		return this.orcamento;
	}

	public String getFabricsPreferences() {
		return this.fabricsPreferences;
	}

	public void setFabricsPreferences(String fabricsPreferences) {
		this.fabricsPreferences = fabricsPreferences;
	}

	public String getPeçasExcluidas() {
		return this.peçasExcluidas;
	}

	public void setPeçasExcluidas(String peçasExcluidas) {
		this.peçasExcluidas = peçasExcluidas;
	}

	public String getOccasions() {
		return this.occasions;
	}

	public void setOccasions(String occasions) {
		this.occasions = occasions;
	}

	public void setCliente(Cliente value) {
		this.cliente = value;
	}

	public Cliente getCliente() {
		return this.cliente;
	}
	
	public String toString() {
		return String.valueOf(getIDPedido());
	}
	
}
