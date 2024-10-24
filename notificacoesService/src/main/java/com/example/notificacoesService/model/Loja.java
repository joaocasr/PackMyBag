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
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="Loja")
public class Loja implements Serializable {
	public Loja() {
	}
	
	@Column(name="IDLoja", nullable=false, length=10)	
	@Id	
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="NOTIFICACOESSERVICE_LOJA_IDLOJA_GENERATOR")
	@SequenceGenerator(name="NOTIFICACOESSERVICE_LOJA_IDLOJA_GENERATOR", sequenceName = "NOTIFICACOESSERVICE_LOJA_IDLOJA_SEQ")
	private int IDLoja;
	
	@Column(name="Nome", nullable=true, length=255)	
	private String nome;

	public Loja(String nome, int IDLoja) {
		this.nome = nome;
		this.IDLoja = IDLoja;
	}

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
	
	public String toString() {
		return String.valueOf(getIDLoja());
	}
	
}
