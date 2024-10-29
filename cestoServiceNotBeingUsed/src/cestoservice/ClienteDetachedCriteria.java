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

import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class ClienteDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression IDCliente;
	public final StringExpression nome;
	public final StringExpression username;
	public final StringExpression email;
	public final CollectionExpression cestos;
	public final CollectionExpression historico;
	
	public ClienteDetachedCriteria() {
		super(cestoservice.Cliente.class, cestoservice.ClienteCriteria.class);
		IDCliente = new IntegerExpression("IDCliente", this.getDetachedCriteria());
		nome = new StringExpression("nome", this.getDetachedCriteria());
		username = new StringExpression("username", this.getDetachedCriteria());
		email = new StringExpression("email", this.getDetachedCriteria());
		cestos = new CollectionExpression("cestos", this.getDetachedCriteria());
		historico = new CollectionExpression("historico", this.getDetachedCriteria());
	}
	
	public ClienteDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, cestoservice.ClienteCriteria.class);
		IDCliente = new IntegerExpression("IDCliente", this.getDetachedCriteria());
		nome = new StringExpression("nome", this.getDetachedCriteria());
		username = new StringExpression("username", this.getDetachedCriteria());
		email = new StringExpression("email", this.getDetachedCriteria());
		cestos = new CollectionExpression("cestos", this.getDetachedCriteria());
		historico = new CollectionExpression("historico", this.getDetachedCriteria());
	}
	
	public cestoservice.CestoDetachedCriteria createCestosCriteria() {
		return new cestoservice.CestoDetachedCriteria(createCriteria("cestos"));
	}
	
	public cestoservice.PagamentoDetachedCriteria createHistoricoCriteria() {
		return new cestoservice.PagamentoDetachedCriteria(createCriteria("historico"));
	}
	
	public Cliente uniqueCliente(PersistentSession session) {
		return (Cliente) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Cliente[] listCliente(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Cliente[]) list.toArray(new Cliente[list.size()]);
	}
}

