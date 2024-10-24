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

import main.java.com.exemplo.encomendaService.model.Loja;

public class LojaCriteria extends AbstractORMCriteria {
	public final IntegerExpression IDLoja;
	public final StringExpression nome;
	public final StringExpression marca;
	public final StringExpression localizacao;
	public final CollectionExpression encomendas;
	
	public LojaCriteria(Criteria criteria) {
		super(criteria);
		IDLoja = new IntegerExpression("IDLoja", this);
		nome = new StringExpression("nome", this);
		marca = new StringExpression("marca", this);
		localizacao = new StringExpression("localizacao", this);
		encomendas = new CollectionExpression("encomendas", this);
	}
	
	public LojaCriteria(PersistentSession session) {
		this(session.createCriteria(Loja.class));
	}
	
	public LojaCriteria() throws PersistentException {
		this(main.java.com.exemplo.encomendaService.criteria.EncomendaServicePersistentManager.instance().getSession());
	}
	
	public encomendaservice.EncomendaCriteria createEncomendasCriteria() {
		return new encomendaservice.EncomendaCriteria(createCriteria("encomendas"));
	}
	
	public Loja uniqueLoja() {
		return (Loja) super.uniqueResult();
	}
	
	public Loja[] listLoja() {
		java.util.List list = super.list();
		return (Loja[]) list.toArray(new Loja[list.size()]);
	}
}

