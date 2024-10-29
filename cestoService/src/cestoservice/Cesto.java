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
package cestoservice;

import java.io.Serializable;
import javax.persistence.*;
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="Cesto")
public class Cesto implements Serializable {
	public Cesto() {
	}
	
	@Column(name="IDCesto", nullable=false, length=10)	
	@Id	
	@GeneratedValue(generator="CESTOSERVICE_CESTO_IDCESTO_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="CESTOSERVICE_CESTO_IDCESTO_GENERATOR", strategy="native")	
	private int IDCesto;
	
	@OneToMany(orphanRemoval=true, targetEntity=cestoservice.Item.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.ALL})	
	@JoinColumns({ @JoinColumn(name="CestoIDCesto", nullable=false) })	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set itens = new java.util.HashSet();
	
	private void setIDCesto(int value) {
		this.IDCesto = value;
	}
	
	public int getIDCesto() {
		return IDCesto;
	}
	
	public int getORMID() {
		return getIDCesto();
	}
	
	public void setItens(java.util.Set value) {
		this.itens = value;
	}
	
	public java.util.Set getItens() {
		return itens;
	}
	
	
	public String toString() {
		return String.valueOf(getIDCesto());
	}
	
}
