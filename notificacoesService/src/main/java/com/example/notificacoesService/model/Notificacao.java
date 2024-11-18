package com.example.notificacoesService.model; /**
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

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="Notificacao")
public class Notificacao implements Serializable {
	public Notificacao() {
	}

	@Column(name="IDNotificacao", nullable=false, length=10)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="NOTIFICACOESSERVICE_NOTIFICACAO_IDNOTIFICACAO_GENERATOR")
	@SequenceGenerator(name="NOTIFICACOESSERVICE_NOTIFICACAO_IDNOTIFICACAO_GENERATOR", sequenceName = "NOTIFICACOESSERVICE_NOTIFICACAO_IDNOTIFICACAO_SEQ")
	private int IDNotificacao;

	@ManyToOne(targetEntity=Cliente.class, fetch=FetchType.LAZY)
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})
	@JoinColumns(value={ @JoinColumn(name="ClienteIDCliente", referencedColumnName="IDCliente", nullable=false) }, foreignKey=@ForeignKey(name="FKNotificaca757747"))
	private Cliente cliente;

	@Column(name="Tipo", nullable=true, length=255)
	private String tipo;

	@Column(name="Descricao", nullable=true, length=255)
	private String descricao;

	@Column(name="Data", nullable=true, length=255)
	private String data;

	public Notificacao(Cliente cliente, String tipo, String descricao, String data) {
		this.cliente = cliente;
		this.tipo = tipo;
		this.descricao = descricao;
		this.data = data;
	}

	private void setIDNotificacao(int value) {
		this.IDNotificacao = value;
	}

	public int getIDNotificacao() {
		return IDNotificacao;
	}

	public int getORMID() {
		return getIDNotificacao();
	}

	public void setTipo(String value) {
		this.tipo = value;
	}

	public String getTipo() {
		return tipo;
	}

	public void setDescricao(String value) {
		this.descricao = value;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setData(String value) {
		this.data = value;
	}

	public String getData() {
		return data;
	}

	public void setCliente(Cliente value) {
		this.cliente = value;
	}

	public Cliente getCliente() {
		return cliente;
	}

	@Override
	public String toString() {
		return "Notificacao{" +
				"IDNotificacao=" + IDNotificacao +
				", cliente=" + cliente +
				", tipo='" + tipo + '\'' +
				", descricao='" + descricao + '\'' +
				", data='" + data + '\'' +
				'}';
	}
}