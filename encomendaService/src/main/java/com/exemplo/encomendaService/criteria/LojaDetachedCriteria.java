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

import main.java.com.exemplo.encomendaService.model.Loja;

public class LojaDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression IDLoja;
	public final StringExpression nome;
	public final StringExpression marca;
	public final StringExpression localizacao;
	public final CollectionExpression encomendas;
	
	public LojaDetachedCriteria() {
		super(main.java.com.exemplo.encomendaService.model.Loja.class, encomendaservice.LojaCriteria.class);
		IDLoja = new IntegerExpression("IDLoja", this.getDetachedCriteria());
		nome = new StringExpression("nome", this.getDetachedCriteria());
		marca = new StringExpression("marca", this.getDetachedCriteria());
		localizacao = new StringExpression("localizacao", this.getDetachedCriteria());
		encomendas = new CollectionExpression("encomendas", this.getDetachedCriteria());
	}
	
	public LojaDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, encomendaservice.LojaCriteria.class);
		IDLoja = new IntegerExpression("IDLoja", this.getDetachedCriteria());
		nome = new StringExpression("nome", this.getDetachedCriteria());
		marca = new StringExpression("marca", this.getDetachedCriteria());
		localizacao = new StringExpression("localizacao", this.getDetachedCriteria());
		encomendas = new CollectionExpression("encomendas", this.getDetachedCriteria());
	}
	
	public encomendaservice.EncomendaDetachedCriteria createEncomendasCriteria() {
		return new encomendaservice.EncomendaDetachedCriteria(createCriteria("encomendas"));
	}
	
	public Loja uniqueLoja(PersistentSession session) {
		return (Loja) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Loja[] listLoja(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Loja[]) list.toArray(new Loja[list.size()]);
	}
}

