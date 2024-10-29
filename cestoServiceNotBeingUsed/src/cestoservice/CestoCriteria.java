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

public class CestoCriteria extends AbstractORMCriteria {
	public final IntegerExpression IDCesto;
	public final CollectionExpression itens;
	
	public CestoCriteria(Criteria criteria) {
		super(criteria);
		IDCesto = new IntegerExpression("IDCesto", this);
		itens = new CollectionExpression("itens", this);
	}
	
	public CestoCriteria(PersistentSession session) {
		this(session.createCriteria(Cesto.class));
	}
	
	public CestoCriteria() throws PersistentException {
		this(cestoservice.CestoServicePersistentManager.instance().getSession());
	}
	
	public cestoservice.ItemCriteria createItensCriteria() {
		return new cestoservice.ItemCriteria(createCriteria("itens"));
	}
	
	public Cesto uniqueCesto() {
		return (Cesto) super.uniqueResult();
	}
	
	public Cesto[] listCesto() {
		java.util.List list = super.list();
		return (Cesto[]) list.toArray(new Cesto[list.size()]);
	}
}

