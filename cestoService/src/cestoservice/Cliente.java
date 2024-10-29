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
@Table(name="Cliente")
public class Cliente implements Serializable {
	public Cliente() {
	}
	
	@Column(name="IDCliente", nullable=false, length=10)	
	@Id	
	@GeneratedValue(generator="CESTOSERVICE_CLIENTE_IDCLIENTE_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="CESTOSERVICE_CLIENTE_IDCLIENTE_GENERATOR", strategy="native")	
	private int IDCliente;
	
	@Column(name="Nome", nullable=true, length=255)	
	private String nome;
	
	@Column(name="Username", nullable=true, length=255)	
	private String username;
	
	@Column(name="Email", nullable=true, length=255)	
	private String email;
	
	@OneToMany(orphanRemoval=true, targetEntity=cestoservice.Cesto.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.ALL})	
	@JoinColumns({ @JoinColumn(name="ClienteIDCliente", nullable=false) })	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set cestos = new java.util.HashSet();
	
	@OneToMany(targetEntity=cestoservice.Pagamento.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="ClienteIDCliente", nullable=false) })	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set historico = new java.util.HashSet();
	
	private void setIDCliente(int value) {
		this.IDCliente = value;
	}
	
	public int getIDCliente() {
		return IDCliente;
	}
	
	public int getORMID() {
		return getIDCliente();
	}
	
	public void setNome(String value) {
		this.nome = value;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setUsername(String value) {
		this.username = value;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setEmail(String value) {
		this.email = value;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setCestos(java.util.Set value) {
		this.cestos = value;
	}
	
	public java.util.Set getCestos() {
		return cestos;
	}
	
	
	public void setHistorico(java.util.Set value) {
		this.historico = value;
	}
	
	public java.util.Set getHistorico() {
		return historico;
	}
	
	
	public String toString() {
		return String.valueOf(getIDCliente());
	}
	
}
