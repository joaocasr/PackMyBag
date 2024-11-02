package com.example.utilizadoresService.model;

import java.io.Serializable;
import jakarta.persistence.*;
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="Estilista")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorValue("Estilista")
@PrimaryKeyJoinColumn(name="ClienteIDCliente", referencedColumnName="IDCliente")
public class Estilista extends Cliente implements Serializable {
	public Estilista() {
	}

	@Column(name="Rating", nullable=true)
	private int rating;

	@Column(name="Genero", nullable=true, length=255)
	private String genero;

	@Column(name="Biografia", nullable=true, length=255)
	private String bio;


	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public Estilista(String nome, String username, String email, String password, String profileImage, int rating, String genero, String bio) {
		super(nome, username, email, password, profileImage);
		this.rating = rating;
		this.genero = genero;
		this.bio = bio;
	}

	public String toString() {
		return super.toString();
	}
	
}
