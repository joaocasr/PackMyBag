/**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

/**
 * Licensee: pg53923(University of Minho)
 * License Type: Academic
 */
package main.java.com.exemplo.encomendaService.criteria;

import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.orm.PersistentSession;
import org.orm.criteria.*;

import main.java.com.exemplo.encomendaService.model.Cliente;

public class ClienteDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression IDCliente;
	public final StringExpression nome;
	public final StringExpression username;
	public final StringExpression email;
	
	public ClienteDetachedCriteria() {
		super(main.java.com.exemplo.encomendaService.model.Cliente.class, encomendaservice.ClienteCriteria.class);
		IDCliente = new IntegerExpression("IDCliente", this.getDetachedCriteria());
		nome = new StringExpression("nome", this.getDetachedCriteria());
		username = new StringExpression("username", this.getDetachedCriteria());
		email = new StringExpression("email", this.getDetachedCriteria());
	}
	
	public ClienteDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, encomendaservice.ClienteCriteria.class);
		IDCliente = new IntegerExpression("IDCliente", this.getDetachedCriteria());
		nome = new StringExpression("nome", this.getDetachedCriteria());
		username = new StringExpression("username", this.getDetachedCriteria());
		email = new StringExpression("email", this.getDetachedCriteria());
	}
	
	public Cliente uniqueCliente(PersistentSession session) {
		return (Cliente) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Cliente[] listCliente(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Cliente[]) list.toArray(new Cliente[list.size()]);
	}
}

