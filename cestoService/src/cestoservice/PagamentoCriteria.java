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

public class PagamentoCriteria extends AbstractORMCriteria {
	public final IntegerExpression IDPagamento;
	public final DoubleExpression total;
	public final StringExpression moeda;
	public final StringExpression localEntrega;
	public final StringExpression inicioAluguer;
	public final StringExpression fimAluguer;
	public final StringExpression codigo;
	
	public PagamentoCriteria(Criteria criteria) {
		super(criteria);
		IDPagamento = new IntegerExpression("IDPagamento", this);
		total = new DoubleExpression("total", this);
		moeda = new StringExpression("moeda", this);
		localEntrega = new StringExpression("localEntrega", this);
		inicioAluguer = new StringExpression("inicioAluguer", this);
		fimAluguer = new StringExpression("fimAluguer", this);
		codigo = new StringExpression("codigo", this);
	}
	
	public PagamentoCriteria(PersistentSession session) {
		this(session.createCriteria(Pagamento.class));
	}
	
	public PagamentoCriteria() throws PersistentException {
		this(cestoservice.CestoServicePersistentManager.instance().getSession());
	}
	
	public Pagamento uniquePagamento() {
		return (Pagamento) super.uniqueResult();
	}
	
	public Pagamento[] listPagamento() {
		java.util.List list = super.list();
		return (Pagamento[]) list.toArray(new Pagamento[list.size()]);
	}
}

