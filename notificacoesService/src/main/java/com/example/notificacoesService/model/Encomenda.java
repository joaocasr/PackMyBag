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
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="Encomenda")
public class Encomenda implements Subject, Serializable {

	
	
	@Column(name="IDEncomenda", nullable=false, length=10)	
	@Id	
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="NOTIFICACOESSERVICE_ENCOMENDA_IDENCOMENDA_GENERATOR")
	@SequenceGenerator(name="NOTIFICACOESSERVICE_ENCOMENDA_IDENCOMENDA_GENERATOR", sequenceName = "NOTIFICACOESSERVICE_ENCOMENDA_IDENCOMENDA_SEQ")
	private int IDEncomenda;
	
	@ManyToOne(targetEntity=Cliente.class, fetch=FetchType.LAZY)
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns(value={ @JoinColumn(name="ClienteIDCliente", referencedColumnName="IDCliente", nullable=false) }, foreignKey=@ForeignKey(name="FKEncomenda932418"))	
	private Cliente cliente;
	
	@Column(name="CodigoEncomenda", nullable=true, length=255)	
	private String codigoEncomenda;
	
	@Column(name="Status", nullable=true, length=255)	
	private String status;

	@Transient
	private List<Observer> observers = new ArrayList<>();
	
	public Encomenda(){}
	public Encomenda(Cliente cliente,String codigo,String status) {
		this.observers = new ArrayList<>();
		this.codigoEncomenda = codigo;
		this.status = status;
		this.cliente = cliente;
	}

	
	private void setIDEncomenda(int value) {
		this.IDEncomenda = value;
	}
	
	public int getIDEncomenda() {
		return IDEncomenda;
	}
	
	public int getORMID() {
		return getIDEncomenda();
	}
	
	public void setCodigoEncomenda(String value) {
		this.codigoEncomenda = value;
	}
	
	public String getCodigoEncomenda() {
		return codigoEncomenda;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setCliente(Cliente value) {
		this.cliente = value;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public String toString() {
		return String.valueOf(getIDEncomenda());
	}

	@Override
	public void registerObserver(Observer o) {
		observers.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		observers.remove(o);
	}

	@Override
	public void notifyObservers() {
		for (Observer observer : observers) {
			observer.update(this);
		}
	}
}