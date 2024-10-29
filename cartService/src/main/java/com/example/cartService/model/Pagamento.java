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
@Table(name="Pagamento")
public class Pagamento implements Serializable {
	public Pagamento() {
	}
	
	@Column(name="IDPagamento", nullable=false, length=10)	
	@Id	
	@GeneratedValue(generator="CESTOSERVICE_PAGAMENTO_IDPAGAMENTO_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="CESTOSERVICE_PAGAMENTO_IDPAGAMENTO_GENERATOR", strategy="native")	
	private int IDPagamento;
	
	@Column(name="Total", nullable=false)	
	private double total;
	
	@Column(name="Moeda", nullable=true, length=255)	
	private String moeda;
	
	@Column(name="LocalEntrega", nullable=true, length=255)	
	private String localEntrega;
	
	@Column(name="InicioAluguer", nullable=true, length=255)	
	private String inicioAluguer;
	
	@Column(name="FimAluguer", nullable=true, length=255)	
	private String fimAluguer;
	
	@Column(name="Codigo", nullable=true, length=255)	
	private String codigo;
	
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
	
	public void setMoeda(String value) {
		this.moeda = value;
	}
	
	public String getMoeda() {
		return moeda;
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
	
	public String toString() {
		return String.valueOf(getIDPagamento());
	}
	
}
