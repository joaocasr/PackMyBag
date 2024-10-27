package com.example.utilizadoresService.model;

import java.io.Serializable;
import jakarta.persistence.*;
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="Loja")
public class Loja implements Serializable {
	public Loja() {
	}
	
	@Column(name="IDLoja", nullable=false, length=10)	
	@Id	
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="LOJA_IDLOJA_GENERATOR")
	@SequenceGenerator(name="LOJA_IDLOJA_GENERATOR", sequenceName="LOJA_IDLOJA_SEQ")
	private int IDLoja;
	
	@Column(name="Nome", nullable=true, length=255)	
	private String nome;
	
	@Column(name="Marca", nullable=true, length=255)	
	private String marca;
	
	@Column(name="Localizacao", nullable=true, length=255)	
	private String localizacao;
	
	private void setIDLoja(int value) {
		this.IDLoja = value;
	}

	public Loja(String nome, String marca, String localizacao) {
		this.nome = nome;
		this.marca = marca;
		this.localizacao = localizacao;
	}

	public int getIDLoja() {
		return IDLoja;
	}
	
	public int getORMID() {
		return getIDLoja();
	}
	
	public void setNome(String value) {
		this.nome = value;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setMarca(String value) {
		this.marca = value;
	}
	
	public String getMarca() {
		return marca;
	}
	
	public void setLocalizacao(String value) {
		this.localizacao = value;
	}
	
	public String getLocalizacao() {
		return localizacao;
	}
	
	public String toString() {
		return String.valueOf(getIDLoja());
	}
	
}
