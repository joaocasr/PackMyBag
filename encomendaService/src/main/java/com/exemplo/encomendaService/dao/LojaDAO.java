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
package main.java.com.exemplo.encomendaService.dao;

import org.orm.*;

import main.java.com.exemplo.encomendaService.criteria.EncomendaServicePersistentManager;
import main.java.com.exemplo.encomendaService.model.Loja;

import org.hibernate.Query;
import org.hibernate.LockMode;
import java.util.List;

public class LojaDAO {
	public static Loja loadLojaByORMID(int IDLoja) throws PersistentException {
		try {
			PersistentSession session = main.java.com.exemplo.encomendaService.criteria.EncomendaServicePersistentManager.instance().getSession();
			return loadLojaByORMID(session, IDLoja);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Loja getLojaByORMID(int IDLoja) throws PersistentException {
		try {
			PersistentSession session = main.java.com.exemplo.encomendaService.criteria.EncomendaServicePersistentManager.instance().getSession();
			return getLojaByORMID(session, IDLoja);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Loja loadLojaByORMID(int IDLoja, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = main.java.com.exemplo.encomendaService.criteria.EncomendaServicePersistentManager.instance().getSession();
			return loadLojaByORMID(session, IDLoja, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Loja getLojaByORMID(int IDLoja, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = main.java.com.exemplo.encomendaService.criteria.EncomendaServicePersistentManager.instance().getSession();
			return getLojaByORMID(session, IDLoja, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Loja loadLojaByORMID(PersistentSession session, int IDLoja) throws PersistentException {
		try {
			return (Loja) session.load(main.java.com.exemplo.encomendaService.model.Loja.class, Integer.valueOf(IDLoja));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Loja getLojaByORMID(PersistentSession session, int IDLoja) throws PersistentException {
		try {
			return (Loja) session.get(main.java.com.exemplo.encomendaService.model.Loja.class, Integer.valueOf(IDLoja));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Loja loadLojaByORMID(PersistentSession session, int IDLoja, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Loja) session.load(main.java.com.exemplo.encomendaService.model.Loja.class, Integer.valueOf(IDLoja), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Loja getLojaByORMID(PersistentSession session, int IDLoja, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Loja) session.get(main.java.com.exemplo.encomendaService.model.Loja.class, Integer.valueOf(IDLoja), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryLoja(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = main.java.com.exemplo.encomendaService.criteria.EncomendaServicePersistentManager.instance().getSession();
			return queryLoja(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryLoja(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = main.java.com.exemplo.encomendaService.criteria.EncomendaServicePersistentManager.instance().getSession();
			return queryLoja(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Loja[] listLojaByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = main.java.com.exemplo.encomendaService.criteria.EncomendaServicePersistentManager.instance().getSession();
			return listLojaByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Loja[] listLojaByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = main.java.com.exemplo.encomendaService.criteria.EncomendaServicePersistentManager.instance().getSession();
			return listLojaByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryLoja(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From encomendaservice.Loja as Loja");
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
	
	public static List queryLoja(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From encomendaservice.Loja as Loja");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Loja", lockMode);
			return query.list();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Loja[] listLojaByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryLoja(session, condition, orderBy);
			return (Loja[]) list.toArray(new Loja[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Loja[] listLojaByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			List list = queryLoja(session, condition, orderBy, lockMode);
			return (Loja[]) list.toArray(new Loja[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Loja loadLojaByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = main.java.com.exemplo.encomendaService.criteria.EncomendaServicePersistentManager.instance().getSession();
			return loadLojaByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Loja loadLojaByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = main.java.com.exemplo.encomendaService.criteria.EncomendaServicePersistentManager.instance().getSession();
			return loadLojaByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Loja loadLojaByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		Loja[] lojas = listLojaByQuery(session, condition, orderBy);
		if (lojas != null && lojas.length > 0)
			return lojas[0];
		else
			return null;
	}
	
	public static Loja loadLojaByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		Loja[] lojas = listLojaByQuery(session, condition, orderBy, lockMode);
		if (lojas != null && lojas.length > 0)
			return lojas[0];
		else
			return null;
	}
	
	public static java.util.Iterator iterateLojaByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = main.java.com.exemplo.encomendaService.criteria.EncomendaServicePersistentManager.instance().getSession();
			return iterateLojaByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateLojaByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = main.java.com.exemplo.encomendaService.criteria.EncomendaServicePersistentManager.instance().getSession();
			return iterateLojaByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateLojaByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From encomendaservice.Loja as Loja");
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
	
	public static java.util.Iterator iterateLojaByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From encomendaservice.Loja as Loja");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Loja", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Loja createLoja() {
		return new main.java.com.exemplo.encomendaService.model.Loja();
	}
	
	public static boolean save(main.java.com.exemplo.encomendaService.model.Loja loja) throws PersistentException {
		try {
			main.java.com.exemplo.encomendaService.criteria.EncomendaServicePersistentManager.instance().saveObject(loja);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean delete(main.java.com.exemplo.encomendaService.model.Loja loja) throws PersistentException {
		try {
			main.java.com.exemplo.encomendaService.criteria.EncomendaServicePersistentManager.instance().deleteObject(loja);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean refresh(main.java.com.exemplo.encomendaService.model.Loja loja) throws PersistentException {
		try {
			main.java.com.exemplo.encomendaService.criteria.EncomendaServicePersistentManager.instance().getSession().refresh(loja);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean evict(main.java.com.exemplo.encomendaService.model.Loja loja) throws PersistentException {
		try {
			main.java.com.exemplo.encomendaService.criteria.EncomendaServicePersistentManager.instance().getSession().evict(loja);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Loja loadLojaByCriteria(LojaCriteria lojaCriteria) {
		Loja[] lojas = listLojaByCriteria(lojaCriteria);
		if(lojas == null || lojas.length == 0) {
			return null;
		}
		return lojas[0];
	}
	
	public static Loja[] listLojaByCriteria(LojaCriteria lojaCriteria) {
		return lojaCriteria.listLoja();
	}
}
