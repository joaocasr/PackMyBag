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
	
	@Column(name="Nrpedido", nullable=false, length=10)
	private int nrpedido;

	@Column(name="Idloja", nullable=false, length=10)
	private int idLoja;
	
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

	public String toString() {
		return String.valueOf(getIDItem());
	}
	
}
