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

import main.java.com.exemplo.encomendaService.model.Encomenda;

public class EncomendaDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression IDEncomenda;
	public final IntegerExpression clienteId;
	public final AssociationExpression cliente;
	public final StringExpression codigoEncomenda;
	public final StringExpression dataEntrega;
	public final StringExpression devolucao;
	public final StringExpression localEntrega;
	public final StringExpression status;
	public final CollectionExpression items;
	
	public EncomendaDetachedCriteria() {
		super(main.java.com.exemplo.encomendaService.model.Encomenda.class, encomendaservice.EncomendaCriteria.class);
		IDEncomenda = new IntegerExpression("IDEncomenda", this.getDetachedCriteria());
		clienteId = new IntegerExpression("cliente.IDCliente", this.getDetachedCriteria());
		cliente = new AssociationExpression("cliente", this.getDetachedCriteria());
		codigoEncomenda = new StringExpression("codigoEncomenda", this.getDetachedCriteria());
		dataEntrega = new StringExpression("dataEntrega", this.getDetachedCriteria());
		devolucao = new StringExpression("devolucao", this.getDetachedCriteria());
		localEntrega = new StringExpression("localEntrega", this.getDetachedCriteria());
		status = new StringExpression("status", this.getDetachedCriteria());
		items = new CollectionExpression("items", this.getDetachedCriteria());
	}
	
	public EncomendaDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, encomendaservice.EncomendaCriteria.class);
		IDEncomenda = new IntegerExpression("IDEncomenda", this.getDetachedCriteria());
		clienteId = new IntegerExpression("cliente.IDCliente", this.getDetachedCriteria());
		cliente = new AssociationExpression("cliente", this.getDetachedCriteria());
		codigoEncomenda = new StringExpression("codigoEncomenda", this.getDetachedCriteria());
		dataEntrega = new StringExpression("dataEntrega", this.getDetachedCriteria());
		devolucao = new StringExpression("devolucao", this.getDetachedCriteria());
		localEntrega = new StringExpression("localEntrega", this.getDetachedCriteria());
		status = new StringExpression("status", this.getDetachedCriteria());
		items = new CollectionExpression("items", this.getDetachedCriteria());
	}
	
	public ClienteDetachedCriteria createClienteCriteria() {
		return new ClienteDetachedCriteria(createCriteria("cliente"));
	}
	
	public encomendaservice.ItemDetachedCriteria createItemsCriteria() {
		return new encomendaservice.ItemDetachedCriteria(createCriteria("items"));
	}
	
	public Encomenda uniqueEncomenda(PersistentSession session) {
		return (Encomenda) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Encomenda[] listEncomenda(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Encomenda[]) list.toArray(new Encomenda[list.size()]);
	}
}

