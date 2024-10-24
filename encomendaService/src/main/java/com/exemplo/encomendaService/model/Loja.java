package main.java.com.exemplo.encomendaService.model;

import java.io.Serializable;
import javax.persistence.*;
@Entity
//@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="Loja")
public class Loja implements Serializable {
	public Loja() {
	}
	
	@Column(name="IDLoja", nullable=false, length=10)	
	@Id	
	@GeneratedValue(generator="ENCOMENDASERVICE_LOJA_IDLOJA_GENERATOR")	
	//@org.hibernate.annotations.GenericGenerator(name="ENCOMENDASERVICE_LOJA_IDLOJA_GENERATOR", strategy="native")	
	@SequenceGenerator(name="ENCOMENDASERVICE_LOJA_IDLOJA_GENERATOR",sequenceName = "ENCOMENDASERVICE_LOJA_IDLOJA_SEQ")

	private int IDLoja;
	
	@Column(name="Nome", nullable=true, length=255)	
	private String nome;
	
	@Column(name="Marca", nullable=true, length=255)	
	private String marca;
	
	@Column(name="Localizacao", nullable=true, length=255)	
	private String localizacao;
	
	@OneToMany(targetEntity=main.java.com.exemplo.encomendaService.model.Encomenda.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.ALL})	
	@JoinColumns({ @JoinColumn(name="LojaIDLoja", nullable=false) })	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set<Encomenda> encomendas = new java.util.HashSet();
	
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
	
	public void setEncomendas(java.util.Set value) {
		this.encomendas = value;
	}
	
	public java.util.Set getEncomendas() {
		return encomendas;
	}
	
	
	public String toString() {
		return String.valueOf(getIDLoja());
	}
	
}
