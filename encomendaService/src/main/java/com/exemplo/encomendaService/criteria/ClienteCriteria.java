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

import org.hibernate.Criteria;
import org.orm.PersistentException;
import org.orm.PersistentSession;
import org.orm.criteria.*;

import main.java.com.exemplo.encomendaService.model.Cliente;

public class ClienteCriteria extends AbstractORMCriteria {
	public final IntegerExpression IDCliente;
	public final StringExpression nome;
	public final StringExpression username;
	public final StringExpression email;
	
	public ClienteCriteria(Criteria criteria) {
		super(criteria);
		IDCliente = new IntegerExpression("IDCliente", this);
		nome = new StringExpression("nome", this);
		username = new StringExpression("username", this);
		email = new StringExpression("email", this);
	}
	
	public ClienteCriteria(PersistentSession session) {
		this(session.createCriteria(Cliente.class));
	}
	
	public ClienteCriteria() throws PersistentException {
		this(main.java.com.exemplo.encomendaService.criteria.EncomendaServicePersistentManager.instance().getSession());
	}
	
	public Cliente uniqueCliente() {
		return (Cliente) super.uniqueResult();
	}
	
	public Cliente[] listCliente() {
		java.util.List list = super.list();
		return (Cliente[]) list.toArray(new Cliente[list.size()]);
	}
}

