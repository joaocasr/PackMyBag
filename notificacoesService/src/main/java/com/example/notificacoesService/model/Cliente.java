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
	
	
	public String toString() {
		return String.valueOf(getIDCliente());
	}

	@Override
	public void update(Object o) {

		if (o instanceof Item) {
			Item i = (Item) o;
			String msg = "O item de codigo "+i.getCodigo()+" da loja "+i.getLojaId()+" está "+i.getDisponibilidade();
			String tipo = "Disponibilidade de Itens";
			Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String s = formatter.format(new java.util.Date());
			Notificacao n = new Notificacao(this,tipo,msg,s);
			this.notificacoes.add(n);

		}else if (o instanceof Encomenda) {
			
	        Encomenda encomenda = (Encomenda) o;
	        // Lógica para enviar a notificação ao cliente
	        System.out.println("Notificando o cliente " + nome + " sobre a encomenda " + encomenda.getIDEncomenda() + " alteração do status para " + encomenda.getStatus());
	    }
	}
}
