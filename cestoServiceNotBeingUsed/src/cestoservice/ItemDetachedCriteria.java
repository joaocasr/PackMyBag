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

public class ItemDetachedCriteria extends AbstractORMDetachedCriteria {
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
	
	public ItemDetachedCriteria() {
		super(cestoservice.Item.class, cestoservice.ItemCriteria.class);
		IDItem = new IntegerExpression("IDItem", this.getDetachedCriteria());
		codigo = new StringExpression("codigo", this.getDetachedCriteria());
		designacao = new StringExpression("designacao", this.getDetachedCriteria());
		preco = new DoubleExpression("preco", this.getDetachedCriteria());
		nraquisicoes = new IntegerExpression("nraquisicoes", this.getDetachedCriteria());
		estilo = new StringExpression("estilo", this.getDetachedCriteria());
		cor = new StringExpression("cor", this.getDetachedCriteria());
		tamanho = new StringExpression("tamanho", this.getDetachedCriteria());
		genero = new StringExpression("genero", this.getDetachedCriteria());
		disponibilidade = new StringExpression("disponibilidade", this.getDetachedCriteria());
		imagem = new StringExpression("imagem", this.getDetachedCriteria());
	}
	
	public ItemDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, cestoservice.ItemCriteria.class);
		IDItem = new IntegerExpression("IDItem", this.getDetachedCriteria());
		codigo = new StringExpression("codigo", this.getDetachedCriteria());
		designacao = new StringExpression("designacao", this.getDetachedCriteria());
		preco = new DoubleExpression("preco", this.getDetachedCriteria());
		nraquisicoes = new IntegerExpression("nraquisicoes", this.getDetachedCriteria());
		estilo = new StringExpression("estilo", this.getDetachedCriteria());
		cor = new StringExpression("cor", this.getDetachedCriteria());
		tamanho = new StringExpression("tamanho", this.getDetachedCriteria());
		genero = new StringExpression("genero", this.getDetachedCriteria());
		disponibilidade = new StringExpression("disponibilidade", this.getDetachedCriteria());
		imagem = new StringExpression("imagem", this.getDetachedCriteria());
	}
	
	public Item uniqueItem(PersistentSession session) {
		return (Item) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Item[] listItem(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Item[]) list.toArray(new Item[list.size()]);
	}
}

