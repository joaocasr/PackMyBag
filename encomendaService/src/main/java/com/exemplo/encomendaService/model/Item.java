package com.exemplo.encomendaService.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
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

	@Column(name="Nrpedido", nullable=false, length=10)
	private int nrpedido;

	@Column(name="Idloja", nullable=false, length=10)
	private int idLoja;

	@Column(name="ImagemUrl", nullable=true, length=255)
	private String imagemUrl;
	

	@Column(name="Designacao", nullable=true, length=255)
	private String designacao;

	@Column(name="Preco", nullable=true)	
	private double preco;

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
	
	public void setPreco(double value) {
		this.preco = value;
	}

	public String getDesignacao() {
		return designacao;
	}
	
	public void setDesignacao(String designacao) {
		this.designacao = designacao;
	}

	public double getPreco() {
		return preco;
	}

	public void setImagemUrl(String value) {
		this.imagemUrl = value;
	}

	public String getImagemUrl() {
		return imagemUrl;
	}

	public void setNrpedido(int value) {
		this.nrpedido = value;
	}
	
	public int getNrpedido() {
		return nrpedido;
	}

	public void setIdLoja(int value) {
		this.idLoja = value;
	}

	public int getIdLoja() {
		return idLoja;
	}

	@Override
	public String toString() {
		return "Item{" +
				"IDItem=" + IDItem +
				", codigo='" + codigo + '\'' +
				", nrpedido=" + nrpedido +
				", idLoja=" + idLoja +
				", imagemUrl='" + imagemUrl + '\'' +
				", designacao='" + designacao + '\'' +
				", preco=" + preco +
				'}';
	}
}
