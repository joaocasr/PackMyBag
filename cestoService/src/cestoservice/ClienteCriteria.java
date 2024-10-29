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

import org.hibernate.Criteria;
import org.orm.PersistentException;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class ClienteCriteria extends AbstractORMCriteria {
	public final IntegerExpression IDCliente;
	public final StringExpression nome;
	public final StringExpression username;
	public final StringExpression email;
	public final CollectionExpression cestos;
	public final CollectionExpression historico;
	
	public ClienteCriteria(Criteria criteria) {
		super(criteria);
		IDCliente = new IntegerExpression("IDCliente", this);
		nome = new StringExpression("nome", this);
		username = new StringExpression("username", this);
		email = new StringExpression("email", this);
		cestos = new CollectionExpression("cestos", this);
		historico = new CollectionExpression("historico", this);
	}
	
	public ClienteCriteria(PersistentSession session) {
		this(session.createCriteria(Cliente.class));
	}
	
	public ClienteCriteria() throws PersistentException {
		this(cestoservice.CestoServicePersistentManager.instance().getSession());
	}
	
	public cestoservice.CestoCriteria createCestosCriteria() {
		return new cestoservice.CestoCriteria(createCriteria("cestos"));
	}
	
	public cestoservice.PagamentoCriteria createHistoricoCriteria() {
		return new cestoservice.PagamentoCriteria(createCriteria("historico"));
	}
	
	public Cliente uniqueCliente() {
		return (Cliente) super.uniqueResult();
	}
	
	public Cliente[] listCliente() {
		java.util.List list = super.list();
		return (Cliente[]) list.toArray(new Cliente[list.size()]);
	}
}

