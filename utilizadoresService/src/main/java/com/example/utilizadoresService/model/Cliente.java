package com.example.utilizadoresService.model;

import java.io.Serializable;
import jakarta.persistence.*;
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="Cliente")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorValue("Cliente")
public class Cliente implements Serializable {
	public Cliente() {
	}
	
	@Column(name="IDCliente", nullable=false, length=10)	
	@Id	
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="CLIENTE_IDCLIENTE_GENERATOR")
	@SequenceGenerator(name="CLIENTE_IDCLIENTE_GENERATOR", sequenceName="CLIENTE_IDCLIENTE_SEQ")
	private int IDCliente;
	
	@Column(name="Nome", nullable=true, length=255)	
	private String nome;
	
	@Column(name="Username", nullable=true, length=255)	
	private String username;
	
	@Column(name="Email", nullable=true, length=255)	
	private String email;
	
	@Column(name="Password", nullable=true, length=255)	
	private String password;
	
	@Column(name="ProfileImage", nullable=true, length=255)	
	private String profileImage;
	
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
	
	public void setPassword(String value) {
		this.password = value;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setProfileImage(String value) {
		this.profileImage = value;
	}
	
	public String getProfileImage() {
		return profileImage;
	}
	
	public void getAttribute() {
		//TODO: Implement Method
		throw new UnsupportedOperationException();
	}
	
	public void setAttribute(int attribute) {
		//TODO: Implement Method
		throw new UnsupportedOperationException();
	}
	
	public String toString() {
		return String.valueOf(getIDCliente());
	}
	
}
