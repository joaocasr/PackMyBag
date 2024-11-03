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
package com.example.notificacoesService.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.*;
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="Item")
public class Item implements Subject, Serializable {
	public Item() {
	}
	
	@Column(name="IDItem", nullable=false, length=10)	
	@Id	
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="NOTIFICACOESSERVICE_ITEM_IDITEM_GENERATOR")
	@SequenceGenerator(name="NOTIFICACOESSERVICE_ITEM_IDITEM_GENERATOR", sequenceName = "NOTIFICACOESSERVICE_ITEM_IDITEM_SEQ")
	private int IDItem;

	@Column(name="Lojaid", nullable=true, length=255)
	private String lojaid;
	
	@Column(name="Codigo", nullable=true, length=255)	
	private String codigo;
	
	@Column(name="Designacao", nullable=true, length=255)	
	private String designacao;
	
	@Column(name="Disponibilidade", nullable=true, length=255)	
	private String disponibilidade;

	@ManyToMany(mappedBy="items", targetEntity=Cliente.class)
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)
	private java.util.Set<Cliente> interessados = new java.util.HashSet();

	//@Transient
	//private List<Observer> observers = new ArrayList<>();

	public Item(String codigo, String designacao, String disponibilidade, String lojaid) {
		this.codigo = codigo;
		this.designacao = designacao;
		this.disponibilidade = disponibilidade;
		this.interessados = new HashSet<>();
		this.lojaid = lojaid;
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
	
	public void setDisponibilidade(String value) {
		this.disponibilidade = value;
	}
	
	public String getDisponibilidade() {
		return disponibilidade;
	}
	
	public void setInteressados(java.util.Set<Cliente> value) {
		this.interessados = value;
	}
	
	public java.util.Set<Cliente> getInteressados() {
		return interessados;
	}

	public void setLoja(String value) {
		this.lojaid = value;
	}
	
	public String getLojaId() {
		return lojaid;
	}
	
	public String toString() {
		return String.valueOf(getIDItem());
	}

	@Override
	public void registerObserver(Observer o) {
		this.interessados.add((Cliente)o);
	}

	@Override
	public void removeObserver(Observer o) {
		Cliente c = (Cliente)o;
		this.interessados.removeIf(x->x.getUsername().equals(c.getUsername()));
	}

	@Override
	public void notifyObservers(String type,NotificationCallback callback) {
		for(Cliente c : this.interessados){
			c.update(this,callback,type);
		}
	}
}
