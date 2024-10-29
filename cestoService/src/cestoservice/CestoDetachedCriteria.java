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

public class CestoDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression IDCesto;
	public final CollectionExpression itens;
	
	public CestoDetachedCriteria() {
		super(cestoservice.Cesto.class, cestoservice.CestoCriteria.class);
		IDCesto = new IntegerExpression("IDCesto", this.getDetachedCriteria());
		itens = new CollectionExpression("itens", this.getDetachedCriteria());
	}
	
	public CestoDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, cestoservice.CestoCriteria.class);
		IDCesto = new IntegerExpression("IDCesto", this.getDetachedCriteria());
		itens = new CollectionExpression("itens", this.getDetachedCriteria());
	}
	
	public cestoservice.ItemDetachedCriteria createItensCriteria() {
		return new cestoservice.ItemDetachedCriteria(createCriteria("itens"));
	}
	
	public Cesto uniqueCesto(PersistentSession session) {
		return (Cesto) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Cesto[] listCesto(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Cesto[]) list.toArray(new Cesto[list.size()]);
	}
}

