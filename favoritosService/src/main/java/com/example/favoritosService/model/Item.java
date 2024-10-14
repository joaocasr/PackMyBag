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
package com.example.favoritosService.model;

import java.io.Serializable;
import jakarta.persistence.*;




@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="Item")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorValue("Item")
public class Item implements Serializable {
	public Item() {
	}
	
	@Column(name="IDItem", nullable=false, length=10)	
	@Id	
	@GeneratedValue(generator="FAVORITOSSERVICE_ITEM_IDITEM_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="FAVORITOSSERVICE_ITEM_IDITEM_GENERATOR", strategy="native")	
	private int IDItem;
	
	@Column(name="Codigo", nullable=true, length=255)	
	private String codigo;
	
	@Column(name="Designacao", nullable=true, length=255)	
	private String designacao;
	
	@Column(name="Preco", nullable=true)	
	private double preco;
	
	@Column(name="Cor", nullable=true, length=255)	
	private String cor;
	
	@Column(name="Tamanho", nullable=true, length=255)	
	private String tamanho;
	
	@Column(name="Disponibilidade", nullable=true, length=255)	
	private String disponibilidade;
	
	@Column(name="Genero", nullable=true, length=255)	
	private String genero;
	
	private void setIDItem(int value) {
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
	
	public void setPreco(double value) {
		this.preco = value;
	}
	
	public double getPreco() {
		return preco;
	}
	
	public void setCor(String value) {
		this.cor = value;
	}
	
	public String getCor() {
		return cor;
	}
	
	public void setTamanho(String value) {
		this.tamanho = value;
	}
	
	public String getTamanho() {
		return tamanho;
	}
	
	public void setDisponibilidade(String value) {
		this.disponibilidade = value;
	}
	
	public String getDisponibilidade() {
		return disponibilidade;
	}
	
	public void setGenero(String value) {
		this.genero = value;
	}
	
	public String getGenero() {
		return genero;
	}
	
	public String toString() {
		return String.valueOf(getIDItem());
	}
	
}
