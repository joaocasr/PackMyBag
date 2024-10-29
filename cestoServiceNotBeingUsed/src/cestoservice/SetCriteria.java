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

public class SetCriteria extends AbstractORMCriteria {
	public final IntegerExpression IDItem;
	public final StringExpression codigo;
	public final StringExpression designacao;
	public final DoubleExpression preco;
	public final IntegerExpression nraquisicoes;
	public final StringExpression estilo;
	public final StringExpression cor;
	public final StringExpression tamanho;
	public final StringExpression genero;
	public final StringExpression disponibilidade;
	public final StringExpression imagem;
	public final IntegerExpression nrPecas;
	public final CollectionExpression pecas;
	
	public SetCriteria(Criteria criteria) {
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
		disponibilidade = new StringExpression("disponibilidade", this);
		imagem = new StringExpression("imagem", this);
		nrPecas = new IntegerExpression("nrPecas", this);
		pecas = new CollectionExpression("pecas", this);
	}
	
	public SetCriteria(PersistentSession session) {
		this(session.createCriteria(Set.class));
	}
	
	public SetCriteria() throws PersistentException {
		this(cestoservice.CestoServicePersistentManager.instance().getSession());
	}
	
	public cestoservice.PecaCriteria createPecasCriteria() {
		return new cestoservice.PecaCriteria(createCriteria("pecas"));
	}
	
	public Set uniqueSet() {
		return (Set) super.uniqueResult();
	}
	
	public Set[] listSet() {
		java.util.List list = super.list();
		return (Set[]) list.toArray(new Set[list.size()]);
	}
}

