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

public class PagamentoDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression IDPagamento;
	public final DoubleExpression total;
	public final StringExpression moeda;
	public final StringExpression localEntrega;
	public final StringExpression inicioAluguer;
	public final StringExpression fimAluguer;
	public final StringExpression codigo;
	
	public PagamentoDetachedCriteria() {
		super(cestoservice.Pagamento.class, cestoservice.PagamentoCriteria.class);
		IDPagamento = new IntegerExpression("IDPagamento", this.getDetachedCriteria());
		total = new DoubleExpression("total", this.getDetachedCriteria());
		moeda = new StringExpression("moeda", this.getDetachedCriteria());
		localEntrega = new StringExpression("localEntrega", this.getDetachedCriteria());
		inicioAluguer = new StringExpression("inicioAluguer", this.getDetachedCriteria());
		fimAluguer = new StringExpression("fimAluguer", this.getDetachedCriteria());
		codigo = new StringExpression("codigo", this.getDetachedCriteria());
	}
	
	public PagamentoDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, cestoservice.PagamentoCriteria.class);
		IDPagamento = new IntegerExpression("IDPagamento", this.getDetachedCriteria());
		total = new DoubleExpression("total", this.getDetachedCriteria());
		moeda = new StringExpression("moeda", this.getDetachedCriteria());
		localEntrega = new StringExpression("localEntrega", this.getDetachedCriteria());
		inicioAluguer = new StringExpression("inicioAluguer", this.getDetachedCriteria());
		fimAluguer = new StringExpression("fimAluguer", this.getDetachedCriteria());
		codigo = new StringExpression("codigo", this.getDetachedCriteria());
	}
	
	public Pagamento uniquePagamento(PersistentSession session) {
		return (Pagamento) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Pagamento[] listPagamento(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Pagamento[]) list.toArray(new Pagamento[list.size()]);
	}
}

