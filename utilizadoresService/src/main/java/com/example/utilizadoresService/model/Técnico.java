package com.example.utilizadoresService.model;

import java.io.Serializable;
import jakarta.persistence.*;
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="Técnico")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorValue("Técnico")
@PrimaryKeyJoinColumn(name="ClienteIDCliente", referencedColumnName="IDCliente")
public class Técnico extends Cliente implements Serializable {
	public Técnico() {
	}
	
	@ManyToOne(targetEntity=Loja.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK, org.hibernate.annotations.CascadeType.DELETE})	
	@JoinColumns(value={ @JoinColumn(name="LojaIDLoja", referencedColumnName="IDLoja", nullable=false) }, foreignKey=@ForeignKey(name="FKTécnico352343"))	
	private Loja loja;
	
	public void setLoja(Loja value) {
		this.loja = value;
	}
	
	public Loja getLoja() {
		return loja;
	}
	
	public String toString() {
		return super.toString();
	}
	
}
