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
@Table(name="Item")
public class Item implements Serializable {
	public Item() {
	}
	
	@Column(name="IDItem", nullable=false, length=10)	
	@Id	
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="RECOMENDACOESSERVICE_ITEM_IDITEM_GENERATOR")
	@SequenceGenerator(name="RECOMENDACOESSERVICE_ITEM_IDITEM_GENERATOR",sequenceName="RECOMENDACOESSERVICE_ITEM_IDITEM_SEQ")
	private int IDItem;
	
	@Column(name="Codigo", nullable=true, length=255)	
	private String codigo;
	
	@Column(name="Designacao", nullable=true, length=255)	
	private String designacao;

	@Column(name="Imagem", nullable=true, length=255)
	private String imagem;

	@Column(name="Preco", nullable=false)
	private double preco;

	@Column(name="Identificador", nullable=false, length=10)
	private int identificador;
	
	@Column(name="IdLoja", nullable=false, length=10)	
	private int idLoja;
	
	public void setIDItem(int value) {
		this.IDItem = value;
	}
	
	public int getIDItem() {
		return IDItem;
	}
	
	public int getORMID() {
		return getIDItem();
	}
	
	public void setCodigo(String value) {
		this.codigo = value;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setDesignacao(String value) {
		this.designacao = value;
	}
	
	public String getDesignacao() {
		return designacao;
	}

	public String getImagem() {
		return this.imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public double getPreco() {
		return this.preco;
	}

	public int getIdentificador() {
		return this.identificador;
	}

	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}

	public void setPreco(double value) {
		this.preco = value;
	}

	public void setIdLoja(int value) {
		this.idLoja = value;
	}
	
	public int getIdLoja() {
		return idLoja;
	}
	
	public String toString() {
		return String.valueOf(getIDItem());
	}
	
}
