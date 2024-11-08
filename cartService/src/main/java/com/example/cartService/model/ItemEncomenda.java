/**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

/**
 * Licensee: joao(University of Minho)
 * License Type: Academic
 */
package com.example.cartService.model;

import java.io.Serializable;
import jakarta.persistence.*;
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="ItemEncomenda")
public class ItemEncomenda implements Serializable {
	public ItemEncomenda() {
	}
	
	@Column(name="IDItemEncomenda", nullable=false, length=10)	
	@Id	
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="CESTOSERVICE_ITEMENCOMENDA_IDITEMENCOMENDA_GENERATOR")
	@SequenceGenerator(name="CESTOSERVICE_ITEMENCOMENDA_IDITEMENCOMENDA_GENERATOR", sequenceName="CESTOSERVICE_ITEMENCOMENDA_IDITEMENCOMENDA_SEQ")
	private int IDItemEncomenda;
	
	@Column(name="Codigo", nullable=true, length=255)	
	private String codigo;
	
	@Column(name="IdLoja", nullable=false, length=10)	
	private int idLoja;
	
	@Column(name="Quantidade", nullable=false, length=10)	
	private int quantidade;

	public ItemEncomenda(int quantidade, String codigo, int idLoja) {
		this.quantidade = quantidade;
		this.codigo = codigo;
		this.idLoja = idLoja;
	}

	private void setIDItemEncomenda(int value) {
		this.IDItemEncomenda = value;
	}
	
	public int getIDItemEncomenda() {
		return IDItemEncomenda;
	}
	
	public int getORMID() {
		return getIDItemEncomenda();
	}
	
	public void setCodigo(String value) {
		this.codigo = value;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setIdLoja(int value) {
		this.idLoja = value;
	}
	
	public int getIdLoja() {
		return idLoja;
	}
	
	public void setQuantidade(int value) {
		this.quantidade = value;
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	
	public String toString() {
		return String.valueOf(getIDItemEncomenda());
	}
	
}
