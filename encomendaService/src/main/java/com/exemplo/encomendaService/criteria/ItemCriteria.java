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

import main.java.com.exemplo.encomendaService.model.Item;

public class ItemCriteria extends AbstractORMCriteria {
	public final IntegerExpression IDItem;
	public final StringExpression codigo;
	public final StringExpression designacao;
	public final DoubleExpression preco;
	public final IntegerExpression nraquisicoes;
	public final StringExpression estilo;
	public final StringExpression cor;
	public final StringExpression tamanho;
	public final StringExpression genero;
	
	public ItemCriteria(Criteria criteria) {
		super(criteria);
		IDItem = new IntegerExpression("IDItem", this);
		codigo = new StringExpression("codigo", this);
		designacao = new StringExpression("designacao", this);
		preco = new DoubleExpression("preco", this);
		nraquisicoes = new IntegerExpression("nraquisicoes", this);
		estilo = new StringExpression("estilo", this);
		cor = new StringExpression("cor", this);
		tamanho = new StringExpression("tamanho", this);
		genero = new StringExpression("genero", this);
	}
	
	public ItemCriteria(PersistentSession session) {
		this(session.createCriteria(Item.class));
	}
	
	public ItemCriteria() throws PersistentException {
		this(main.java.com.exemplo.encomendaService.criteria.EncomendaServicePersistentManager.instance().getSession());
	}
	
	public Item uniqueItem() {
		return (Item) super.uniqueResult();
	}
	
	public Item[] listItem() {
		java.util.List list = super.list();
		return (Item[]) list.toArray(new Item[list.size()]);
	}
}

