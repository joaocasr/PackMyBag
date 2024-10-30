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
import java.util.HashSet;

import jakarta.persistence.*;

@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="Item")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorValue("Item")
public class Item implements Serializable {
	
	@Column(name="IDItem", nullable=false, length=10)	
	@Id	
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="FAVORITOSSERVICE_ITEM_IDITEM_GENERATOR")
	@SequenceGenerator(name="FAVORITOSSERVICE_ITEM_IDITEM_GENERATOR", sequenceName="FAVORITOSSERVICE_ITEM_IDITEM_SEQ")
	private int IDItem;

	@Column(name="Codigo", nullable=true, length=255)
	private String codigo;

	@Column(name="Designacao", nullable=true, length=255)
	private String designacao;

	@Column(name="Preco", nullable=true)
	private double preco;

	@Column(name="Disponibilidade", nullable=true, length=255)
	private String disponibilidade;

	@Column(name="Tipo", nullable=true, length=255)
	private String tipo;

	@Column(name="Imagem", nullable=true, length=255)
	private String imagem;

	@Column(name="Subclasse", nullable=true, length=255)
	private String subclasse;

	@Column(name="Dimensao", nullable=true, length=255)
	private String dimensao;

	@Column(name="Idloja", nullable=true)
	private int idloja;

	@Column(name="Identificador", nullable=true)
	private int identificador;

	public Item() {

	}

	public Item(String codigo, String designacao, double preco, String disponibilidade, String tipo, String imagem, String subclasse, String dimensao,int id,int identificador) {
		this.codigo = codigo;
		this.designacao = designacao;
		this.preco = preco;
		this.disponibilidade = disponibilidade;
		this.tipo = tipo;
		this.imagem = imagem;
		this.subclasse = subclasse;
		this.dimensao = dimensao;
		this.idloja=id;
		this.identificador = identificador;
	}


	public String getDimensao() {
		return dimensao;
	}

	public void setDimensao(String dimensao) {
		this.dimensao = dimensao;
	}

	public String getSubclasse() {
		return subclasse;
	}

	public void setSubclasse(String subclasse) {
		this.subclasse = subclasse;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

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

	public void setDisponibilidade(String value) {
		this.disponibilidade = value;
	}
	
	public String getDisponibilidade() {
		return disponibilidade;
	}
	
	public void setGenero(String value) {
		this.tipo = value;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public String toString() {
		return String.valueOf(getIDItem());
	}

	public void setImagem(String value) {
		this.imagem = value;
	}

	public String getImagem() {
		return imagem;
	}

	public void setIdLoja(int value) {
		this.idloja = value;
	}

	public int getIdLoja() {
		return idloja;
	}

	public int getIdentificador() {
		return identificador;
	}

	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}
}
