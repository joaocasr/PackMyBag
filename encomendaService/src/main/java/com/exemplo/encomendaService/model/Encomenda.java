package main.java.com.exemplo.encomendaService.model;

import java.io.Serializable;
import javax.persistence.*;
@Entity
//@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="Encomenda")
public class Encomenda implements Serializable {
	public Encomenda() {
	}
	
	@Column(name="IDEncomenda", nullable=false, length=10)	
	@Id	
	@GeneratedValue(generator="ENCOMENDASERVICE_ENCOMENDA_IDENCOMENDA_GENERATOR")
	@SequenceGenerator(name="ENCOMENDASERVICE_ENCOMENDA_IDENCOMENDA_GENERATOR",sequenceName = "ENCOMENDASERVICE_ENCOMENDA_IDENCOMENDA_SEQ")
	private int IDEncomenda;
	
	@ManyToOne(targetEntity=main.java.com.exemplo.encomendaService.model.Cliente.class, fetch=FetchType.LAZY)	
	//@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK, org.hibernate.annotations.CascadeType.DELETE})	
	@JoinColumns(value={ @JoinColumn(name="ClienteIDCliente", referencedColumnName="IDCliente", nullable=false) }, foreignKey=@ForeignKey(name="FKEncomenda932418"))	
	private main.java.com.exemplo.encomendaService.model.Cliente cliente;
	
	@Column(name="CodigoEncomenda", nullable=true, length=255)	
	private String codigoEncomenda;
	
	@Column(name="DataEntrega", nullable=true, length=255)	
	private String dataEntrega;
	
	@Column(name="Devolucao", nullable=true, length=255)	
	private String devolucao;
	
	@Column(name="LocalEntrega", nullable=true, length=255)	
	private String localEntrega;
	
	@Column(name="Status", nullable=true, length=255)	
	private String status;
	
	@OneToMany(orphanRemoval=true, targetEntity=main.java.com.exemplo.encomendaService.model.Item.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.ALL})	
	@JoinColumns({ @JoinColumn(name="EncomendaIDEncomenda", nullable=false) })	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set<Item> items = new java.util.HashSet();
	
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
	
	public void setDevolucao(String value) {
		this.devolucao = value;
	}
	
	public String getDevolucao() {
		return devolucao;
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
	
	public void setCliente(main.java.com.exemplo.encomendaService.model.Cliente value) {
		this.cliente = value;
	}
	
	public main.java.com.exemplo.encomendaService.model.Cliente getCliente() {
		return cliente;
	}
	
	public void setItems(java.util.Set value) {
		this.items = value;
	}
	
	public java.util.Set getItems() {
		return items;
	}
	
	public String toString() {
		return String.valueOf(getIDEncomenda());
	}
	
}