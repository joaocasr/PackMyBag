/**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

/**
 * Licensee: Afonso Marques(University of Minho)
 * License Type: Academic
 */
package com.example.favoritosService.model;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.persistence.*;
import org.springframework.data.domain.PageRequest;


@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="Cliente")
public class Cliente implements Serializable {

	public Cliente() {
	}
	
	@Column(name="IDCliente", nullable=false, length=10)	
	@Id	
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="FAVORITOSSERVICE_CLIENTE_IDCLIENTE_GENERATOR")
	@SequenceGenerator(name="FAVORITOSSERVICE_CLIENTE_IDCLIENTE_GENERATOR", sequenceName="FAVORITOSSERVICE_CLIENTE_IDCLIENTE_SEQ")
	private int IDCliente;

	@Column(name="Username", nullable=true, length=255)
	private String username;

	@OneToMany(orphanRemoval=true, targetEntity=Item.class)
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.ALL})	
	@JoinColumns({ @JoinColumn(name="ClienteIDCliente", nullable=false) })	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)
	private java.util.Set<Item> favoritos = new java.util.HashSet();

	public Cliente(String username, Set<Item> favoritos) {
		this.username = username;
		this.favoritos = favoritos;
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

	public void setUsername(String value) {
		this.username = value;
	}

	public String getUsername() {
		return username;
	}

	public void setFavoritos(java.util.Set<Item> value) {
		this.favoritos = value;
	}
	
	public java.util.Set<Item> getFavoritos() {
		return favoritos;
	}
	
	public String toString() {
		return String.valueOf(getIDCliente());
	}

	public void addFavorite(Item i){
		this.favoritos.add(i);
	}

	public void removeFavorite(String codigo){
		this.favoritos = this.favoritos.stream().filter(x->!x.getCodigo().equals(codigo)).collect(Collectors.toSet());
	}

	/*
	// filtrar fav por genero
	public java.util.Set<String> getFavItemsByGender(String gender){
		for(String code : this.getFavoritos()){
			if
		}
		return null;
	}

	// filtrar favoritos por preço
	public java.util.Set<String> getFavItemsByPrice(int min, @Param("max") int max, PageRequest pageable){
		this.getFavoritos().
	}

	// filtrar fav por tamanho
	@Query("select c.favoritos as f FROM Cliente c where f.tamanho= :tamanho")
	Page<Item> getFavItemsBySize(@Param("tamanho") String tamanho, PageRequest pageable);

	// filtrar fav por genero e preço
	@Query("select c.favoritos as f FROM Cliente c where f.preco>= :min AND f.preco<= :max AND f.genero= :gender")
	Page<Item> getFavItemsByPriceandGender(@Param("min") int min, @Param("max") int max, @Param("gender") String gender, PageRequest pageable);

	// filtrar fav por genero e tamanho
	@Query("select c.favoritos as f FROM Cliente c where f.tamanho= :tamanho AND f.genero= :gender")
	Page<Item> getFavItemsBySizeandGender(@Param("tamanho") String tamanho, @Param("gender") String gender, PageRequest pageable);

	// filtrar fav por tamanho e preço
	@Query("select c.favoritos as f FROM Cliente c where f.preco>= :min AND f.preco<= :max AND f.tamanho= :tamanho")
	Page<Item> getFavItemsByPriceandSize(@Param("min") int min, @Param("max") int max, @Param("tamanho") String gender, PageRequest pageable);

	// usar todos os filtros para fav
	@Query("select c.favoritos as f FROM Cliente c where f.preco>= :min AND f.preco<= :max AND f.tamanho= :tamanho AND f.genero= :gender")
	Page<Item> getFavItemsByPriceSizeGender(@Param("min") int min, @Param("max") int max, @Param("tamanho") String tamanho, @Param("gender") String gender, PageRequest pageable);

	@Query("select c.favoritos as f FROM Cliente c where f.codigo= :code AND c.username= :username")
	Set<Item> getFavItemsByCode(@Param("username") String username, @Param("code") String code);
	*/
}
