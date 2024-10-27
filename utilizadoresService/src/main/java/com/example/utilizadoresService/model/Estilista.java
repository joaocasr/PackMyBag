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
	
	public String toString() {
		return super.toString();
	}
	
}
