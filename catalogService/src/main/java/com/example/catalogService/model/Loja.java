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
package com.example.catalogService.model;

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
	@GeneratedValue(generator="CATALOGOSERVICE_LOJA_IDLOJA_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="CATALOGOSERVICE_LOJA_IDLOJA_GENERATOR", strategy="native")	
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
