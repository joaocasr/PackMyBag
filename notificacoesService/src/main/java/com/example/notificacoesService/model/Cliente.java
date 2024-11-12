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
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.HashSet;

import jakarta.persistence.*;
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="Cliente")
public class Cliente implements Observer, Serializable {
	public Cliente() {
	}
	
	@Column(name="IDCliente", nullable=false, length=10)	
	@Id	
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="NOTIFICACOESSERVICE_CLIENTE_IDCLIENTE_GENERATOR")
	@SequenceGenerator(name="NOTIFICACOESSERVICE_CLIENTE_IDCLIENTE_GENERATOR", sequenceName = "NOTIFICACOESSERVICE_CLIENTE_IDCLIENTE_SEQ")
	private int IDCliente;

	@Column(name="Nome", nullable=true, length=255)
	private String nome;

	@Column(name="Username", nullable=true, length=255)
	private String username;

	@Column(name="Email", nullable=true, length=255)
	private String email;

	@ManyToMany(targetEntity=Item.class)
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})
	@JoinTable(name="Item_Cliente", joinColumns={ @JoinColumn(name="ClienteIDCliente") }, inverseJoinColumns={ @JoinColumn(name="ItemIDItem") })
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)
	private java.util.Set<Item> items = new java.util.HashSet();

	@OneToMany(mappedBy="cliente", targetEntity=Notificacao.class)
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)
	private java.util.Set<Notificacao> notificacoes = new java.util.HashSet();


	public Cliente(String nome, String email, String username) {
		this.nome = nome;
		this.email = email;
		this.username = username;
		this.notificacoes = new HashSet<>();
		this.items = new HashSet<>();
	}

	private void setIDCliente(int value) {
		this.IDCliente = value;
	}
	
	public int getIDCliente() {
		return IDCliente;
	}
	
	public int getORMID() {
		return getIDCliente();
	}
	
	public void setNome(String value) {
		this.nome = value;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setUsername(String value) {
		this.username = value;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setEmail(String value) {
		this.email = value;
	}
	
	public String getEmail() {
		return email;
	}

	public void setItems(java.util.Set<Item> value) {
		this.items = value;
	}

	public java.util.Set<Item> getItems() {
		return items;
	}

	public void addItem(Item i){
		this.items.add(i);
	}

	public void setNotificacoes(java.util.Set<Notificacao> value) {
		this.notificacoes = value;
	}
	
	public java.util.Set<Notificacao> getNotificacoes() {
		return notificacoes;
	}

	public void removeNotification(Notificacao n){
		this.notificacoes.remove(n);
	}
	
	
	public String toString() {
		return String.valueOf(getIDCliente());
	}

	@Override
	public void update(Object o, NotificationCallback callback,String type) {
		Notificacao n = null;
		if (type.equals("AVAILABILITY")) {
			Item i = (Item) o;
			String msg = "The item with code "+i.getCodigo()+" from shop "+i.getLojaId()+" is "+i.getDisponibilidade();
			String tipo = "Item availability.";
			Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String s = formatter.format(new java.util.Date());
			n = new Notificacao(this,tipo,msg,s);

		}
		else if (type.equals("STATUS")) {

	        Encomenda encomenda = (Encomenda) o;
	        // Lógica para enviar a notificação ao cliente
			String msg = "Your order with code " + encomenda.getCodigoEncomenda() + " changed its status to " + encomenda.getStatus();
			String tipo = "Order status.";
			Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String s = formatter.format(new java.util.Date());
			n = new Notificacao(this,tipo,msg,s);
		}
		else if (type.equals("RETURN")) {

			Encomenda encomenda = (Encomenda) o;
			String msg = "";
			if(encomenda.getDiasRestantes()==0) msg = "You have to deliver your items today!";
			if(encomenda.getDiasRestantes()>0) msg = "You have "+encomenda.getDiasRestantes()+" days left to deliver your items!";
			if(encomenda.getDiasRestantes()<0) msg = "You had to deliver your items "+encomenda.getDiasRestantes()+" days ago! It will be charged a fee.";
			String tipo = "Return items.";
			Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String s = formatter.format(new java.util.Date());
			n = new Notificacao(this,tipo,msg,s);
		}
		if (n != null) {
			this.notificacoes.add(n);
			callback.handleNewNotification(n);
		}
	}
}
