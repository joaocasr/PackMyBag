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

import org.orm.*;
import org.hibernate.Query;
import org.hibernate.LockMode;
import java.util.List;

public class PagamentoDAO {
	public static Pagamento loadPagamentoByORMID(int IDPagamento) throws PersistentException {
		try {
			PersistentSession session = cestoservice.CestoServicePersistentManager.instance().getSession();
			return loadPagamentoByORMID(session, IDPagamento);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Pagamento getPagamentoByORMID(int IDPagamento) throws PersistentException {
		try {
			PersistentSession session = cestoservice.CestoServicePersistentManager.instance().getSession();
			return getPagamentoByORMID(session, IDPagamento);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Pagamento loadPagamentoByORMID(int IDPagamento, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = cestoservice.CestoServicePersistentManager.instance().getSession();
			return loadPagamentoByORMID(session, IDPagamento, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Pagamento getPagamentoByORMID(int IDPagamento, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = cestoservice.CestoServicePersistentManager.instance().getSession();
			return getPagamentoByORMID(session, IDPagamento, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Pagamento loadPagamentoByORMID(PersistentSession session, int IDPagamento) throws PersistentException {
		try {
			return (Pagamento) session.load(cestoservice.Pagamento.class, Integer.valueOf(IDPagamento));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Pagamento getPagamentoByORMID(PersistentSession session, int IDPagamento) throws PersistentException {
		try {
			return (Pagamento) session.get(cestoservice.Pagamento.class, Integer.valueOf(IDPagamento));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Pagamento loadPagamentoByORMID(PersistentSession session, int IDPagamento, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Pagamento) session.load(cestoservice.Pagamento.class, Integer.valueOf(IDPagamento), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Pagamento getPagamentoByORMID(PersistentSession session, int IDPagamento, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Pagamento) session.get(cestoservice.Pagamento.class, Integer.valueOf(IDPagamento), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryPagamento(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = cestoservice.CestoServicePersistentManager.instance().getSession();
			return queryPagamento(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryPagamento(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = cestoservice.CestoServicePersistentManager.instance().getSession();
			return queryPagamento(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Pagamento[] listPagamentoByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = cestoservice.CestoServicePersistentManager.instance().getSession();
			return listPagamentoByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Pagamento[] listPagamentoByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = cestoservice.CestoServicePersistentManager.instance().getSession();
			return listPagamentoByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryPagamento(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From cestoservice.Pagamento as Pagamento");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			return query.list();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryPagamento(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From cestoservice.Pagamento as Pagamento");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Pagamento", lockMode);
			return query.list();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Pagamento[] listPagamentoByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryPagamento(session, condition, orderBy);
			return (Pagamento[]) list.toArray(new Pagamento[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Pagamento[] listPagamentoByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			List list = queryPagamento(session, condition, orderBy, lockMode);
			return (Pagamento[]) list.toArray(new Pagamento[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Pagamento loadPagamentoByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = cestoservice.CestoServicePersistentManager.instance().getSession();
			return loadPagamentoByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Pagamento loadPagamentoByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = cestoservice.CestoServicePersistentManager.instance().getSession();
			return loadPagamentoByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Pagamento loadPagamentoByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		Pagamento[] pagamentos = listPagamentoByQuery(session, condition, orderBy);
		if (pagamentos != null && pagamentos.length > 0)
			return pagamentos[0];
		else
			return null;
	}
	
	public static Pagamento loadPagamentoByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		Pagamento[] pagamentos = listPagamentoByQuery(session, condition, orderBy, lockMode);
		if (pagamentos != null && pagamentos.length > 0)
			return pagamentos[0];
		else
			return null;
	}
	
	public static java.util.Iterator iteratePagamentoByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = cestoservice.CestoServicePersistentManager.instance().getSession();
			return iteratePagamentoByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iteratePagamentoByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = cestoservice.CestoServicePersistentManager.instance().getSession();
			return iteratePagamentoByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iteratePagamentoByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From cestoservice.Pagamento as Pagamento");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			return query.iterate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iteratePagamentoByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From cestoservice.Pagamento as Pagamento");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Pagamento", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Pagamento createPagamento() {
		return new cestoservice.Pagamento();
	}
	
	public static boolean save(cestoservice.Pagamento pagamento) throws PersistentException {
		try {
			cestoservice.CestoServicePersistentManager.instance().saveObject(pagamento);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean delete(cestoservice.Pagamento pagamento) throws PersistentException {
		try {
			cestoservice.CestoServicePersistentManager.instance().deleteObject(pagamento);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean refresh(cestoservice.Pagamento pagamento) throws PersistentException {
		try {
			cestoservice.CestoServicePersistentManager.instance().getSession().refresh(pagamento);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean evict(cestoservice.Pagamento pagamento) throws PersistentException {
		try {
			cestoservice.CestoServicePersistentManager.instance().getSession().evict(pagamento);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Pagamento loadPagamentoByCriteria(PagamentoCriteria pagamentoCriteria) {
		Pagamento[] pagamentos = listPagamentoByCriteria(pagamentoCriteria);
		if(pagamentos == null || pagamentos.length == 0) {
			return null;
		}
		return pagamentos[0];
	}
	
	public static Pagamento[] listPagamentoByCriteria(PagamentoCriteria pagamentoCriteria) {
		return pagamentoCriteria.listPagamento();
	}
}
