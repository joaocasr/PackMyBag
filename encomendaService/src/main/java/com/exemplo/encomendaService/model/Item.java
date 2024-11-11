package com.exemplo.encomendaService.model;

import java.io.Serializable;
import jakarta.persistence.*;
@Entity
//@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="Item")
public class Item implements Serializable {
	public Item() {
	}
	
	@Column(name="IDItem", nullable=false, length=10)	
	@Id	
	@GeneratedValue(generator="ENCOMENDASERVICE_ITEM_IDITEM_GENERATOR")	
	//@org.hibernate.annotations.GenericGenerator(name="ENCOMENDASERVICE_ITEM_IDITEM_GENERATOR", strategy="native")	
	@SequenceGenerator(name="ENCOMENDASERVICE_ITEM_IDITEM_GENERATOR", sequenceName = "ENCOMENDASERVICE_ITEM_IDITEM_SEQ")
	private int IDItem;
	
	@Column(name="Codigo", nullable=true, length=255)	
	private String codigo;
	
	@Column(name="Designacao", nullable=true, length=255)	
	private String designacao;
	
	@Column(name="Preco", nullable=true)	
	private double preco;
	
	@Column(name="Nraquisicoes", nullable=false, length=10)	
	private int nraquisicoes;
	
	@Column(name="Estilo", nullable=true, length=255)	
	private String estilo;
	
	@Column(name="Cor", nullable=true, length=255)	
	private String cor;
	
	@Column(name="Tamanho", nullable=true, length=255)	
	private String tamanho;
	
	@Column(name="Genero", nullable=true, length=255)	
	private String genero;

	@Column(name="ImagemUrl", nullable=true, length=255)
	private String imagemUrl;
	
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
	
	public void setNraquisicoes(int value) {
		this.nraquisicoes = value;
	}
	
	public int getNraquisicoes() {
		return nraquisicoes;
	}
	
	public void setEstilo(String value) {
		this.estilo = value;
	}
	
	public String getEstilo() {
		return estilo;
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
	
	public void setGenero(String value) {
		this.genero = value;
	}
	
	public String getGenero() {
		return genero;
	}
	
	public String toString() {
		return String.valueOf(getIDItem());
	}

	public void setImagemUrl(String value) {
		this.imagemUrl = value;
	}

	public String getImagemUrl() {
		return imagemUrl;
	}
	
}
