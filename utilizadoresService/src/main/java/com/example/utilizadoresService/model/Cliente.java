package com.example.utilizadoresService.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="Cliente")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorValue("Cliente")
public class Cliente implements Serializable, UserDetails {
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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (this instanceof Tecnico) {
			return List.of(new SimpleGrantedAuthority("ROLE_TECNICO"));
		}if (this instanceof Estilista) {
			return List.of(new SimpleGrantedAuthority("ROLE_ESTILISTA"));
		} else {
			return List.of(new SimpleGrantedAuthority("ROLE_USER"));
		}

	}

	public Cliente(String nome, String username, String email, String password, String profileImage) {
		this.nome = nome;
		this.username = username;
		this.email = email;
		this.password = password;
		this.profileImage = profileImage;
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
