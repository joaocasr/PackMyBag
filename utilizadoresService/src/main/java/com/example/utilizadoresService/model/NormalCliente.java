package com.example.utilizadoresService.model;

import java.io.Serializable;
import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;

@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="NormalCliente")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorValue("NormalCliente")
@PrimaryKeyJoinColumn(name="ClienteIDCliente", referencedColumnName="IDCliente")
public class NormalCliente extends Cliente implements Serializable {
	public NormalCliente() {
	}
	
	@Column(name="Morada", nullable=true, length=255)	
	private String morada;
	
	@Column(name="CartaoCredito", nullable=true, length=255)	
	private String cartaoCredito;
	
	@Column(name="NrTelemovel", nullable=true, length=255)	
	private String nrTelemovel;


	@Column(name="Genero", nullable=true, length=255)
	private String genero;


	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	
	public void setMorada(String value) {
		this.morada = value;
	}
	
	public String getMorada() {
		return morada;
	}
	
	public void setCartaoCredito(String value) {
		this.cartaoCredito = value;
	}
	
	public String getCartaoCredito() {
		return cartaoCredito;
	}
	
	public void setNrTelemovel(String value) {
		this.nrTelemovel = value;
	}
	
	public String getNrTelemovel() {
		return nrTelemovel;
	}
	
	public String toString() {
		return super.toString();
	}

	public NormalCliente(String nome, String username, String email, String password, MultipartFile profileImage, String morada, String cartaoCredito, String nrTelemovel, String genero) {
		super(nome, username, email, password, profileImage);
		this.morada = morada;
		this.cartaoCredito = cartaoCredito;
		this.nrTelemovel = nrTelemovel;
		this.genero = genero;
	}
}
