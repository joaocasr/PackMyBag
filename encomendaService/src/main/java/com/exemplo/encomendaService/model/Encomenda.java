package com.exemplo.encomendaService.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
//@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="Encomenda")
public class Encomenda implements Serializable {
	public Encomenda() {
		this.items = new HashSet<>();
	}
	
	@Column(name="IDEncomenda", nullable=false, length=10)	
	@Id	
	@GeneratedValue(generator="ENCOMENDASERVICE_ENCOMENDA_IDENCOMENDA_GENERATOR")
	@SequenceGenerator(name="ENCOMENDASERVICE_ENCOMENDA_IDENCOMENDA_GENERATOR",sequenceName = "ENCOMENDASERVICE_ENCOMENDA_IDENCOMENDA_SEQ")
	private int IDEncomenda;
	
	@ManyToOne(targetEntity=Cliente.class, fetch=FetchType.LAZY)	
	//@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK, org.hibernate.annotations.CascadeType.DELETE})	
	@JoinColumns(value={ @JoinColumn(name="ClienteIDCliente", referencedColumnName="IDCliente", nullable=false) }, foreignKey=@ForeignKey(name="FKEncomenda932418"))	
	private Cliente cliente;

	@Column(name="CodigoEncomenda", nullable=true, length=255)	
	private String codigoEncomenda;
	
	@Column(name="DataEntrega", nullable=true, length=255)	
	private String dataEntrega;
	
	@Column(name="DataDevolucao", nullable=true, length=255)	
	private String dataDevolucao;
	
	@Column(name="LocalEntrega", nullable=true, length=255)	
	private String localEntrega;
	
	@Column(name="Status", nullable=true, length=255)	
	private String status;

	@Column(name="Preco", nullable=true, length=255)
	private Double preco;

	@Column(name="TaxaEntrega", nullable=true, length=255)
	private Double taxaEntrega;
	
	@OneToMany(orphanRemoval=true, targetEntity=Item.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.ALL})	
	@JoinColumns({ @JoinColumn(name="EncomendaIDEncomenda", nullable=false) })	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set<Item> items = new java.util.HashSet();

	// Introduzir optimitisc locking
    // @Version
    // @Column(name = "version", nullable = false)
    // private Long version;
	
	private void setIDEncomenda(int value) {
		this.IDEncomenda = value;
	}
	
	public int getIDEncomenda() {
		return IDEncomenda;
	}
	
	public int getORMID() {
		return getIDEncomenda();
	}
	
	public void setCodigoEncomenda(String value) {
		this.codigoEncomenda = value;
	}
	
	public String getCodigoEncomenda() {
		return codigoEncomenda;
	}
	
	public void setDataEntrega(String value) {
		this.dataEntrega = value;
	}
	
	public String getDataEntrega() {
		return dataEntrega;
	}
	
	public void setDataDevolucao(String value) {
		this.dataDevolucao = value;
	}
	
	public String getDataDevolucao() {
		return dataDevolucao;
	}
	
	public void setLocalEntrega(String value) {
		this.localEntrega = value;
	}
	
	public String getLocalEntrega() {
		return localEntrega;
	}
	
	public void setStatus(String value) {
		this.status = value;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setCliente(Cliente value) {
		this.cliente = value;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setItems(Set<Item> value) {
		this.items = value;
	}
	
	public Set<Item> getItems() {
		return items;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Double getTaxaEntrega() {
		return taxaEntrega;
	}

	public void setTaxaEntrega(Double taxaEntrega) {
		this.taxaEntrega = taxaEntrega;
	}
	public void addItemToEncomenda(Item i){
		this.items.add(i);
	}

    @Override
    public String toString() {
        return "Encomenda{" +
                "codigo='" + codigoEncomenda + '\'' +
                ", items=" + getItems() +
                '}';

    }
	
}