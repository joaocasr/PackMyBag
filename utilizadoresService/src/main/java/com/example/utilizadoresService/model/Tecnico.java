package com.example.utilizadoresService.model;

import java.io.Serializable;
import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;

@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="Tecnico")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorValue("Tecnico")
@PrimaryKeyJoinColumn(name="ClienteIDCliente", referencedColumnName="IDCliente")
public class Tecnico extends Cliente implements Serializable {
	public Tecnico() {
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

	public Tecnico(String nome, String username, String email, String password, String profileimage, Loja loja) {
		super(nome, username, email,  password,profileimage);
		this.loja = loja;
	}
}
