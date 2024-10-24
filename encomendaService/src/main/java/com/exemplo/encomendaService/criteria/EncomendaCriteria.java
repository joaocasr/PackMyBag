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

import main.java.com.exemplo.encomendaService.model.Encomenda;

public class EncomendaCriteria extends AbstractORMCriteria {
	public final IntegerExpression IDEncomenda;
	public final IntegerExpression clienteId;
	public final AssociationExpression cliente;
	public final StringExpression codigoEncomenda;
	public final StringExpression dataEntrega;
	public final StringExpression devolucao;
	public final StringExpression localEntrega;
	public final StringExpression status;
	public final CollectionExpression items;
	
	public EncomendaCriteria(Criteria criteria) {
		super(criteria);
		IDEncomenda = new IntegerExpression("IDEncomenda", this);
		clienteId = new IntegerExpression("cliente.IDCliente", this);
		cliente = new AssociationExpression("cliente", this);
		codigoEncomenda = new StringExpression("codigoEncomenda", this);
		dataEntrega = new StringExpression("dataEntrega", this);
		devolucao = new StringExpression("devolucao", this);
		localEntrega = new StringExpression("localEntrega", this);
		status = new StringExpression("status", this);
		items = new CollectionExpression("items", this);
	}
	
	public EncomendaCriteria(PersistentSession session) {
		this(session.createCriteria(Encomenda.class));
	}
	
	public EncomendaCriteria() throws PersistentException {
		this(main.java.com.exemplo.encomendaService.criteria.EncomendaServicePersistentManager.instance().getSession());
	}
	
	public ClienteCriteria createClienteCriteria() {
		return new ClienteCriteria(createCriteria("cliente"));
	}
	
	public encomendaservice.ItemCriteria createItemsCriteria() {
		return new encomendaservice.ItemCriteria(createCriteria("items"));
	}
	
	public Encomenda uniqueEncomenda() {
		return (Encomenda) super.uniqueResult();
	}
	
	public Encomenda[] listEncomenda() {
		java.util.List list = super.list();
		return (Encomenda[]) list.toArray(new Encomenda[list.size()]);
	}
}

