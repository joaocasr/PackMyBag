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

public class PecaDAO {
	public static Peca loadPecaByORMID(int IDItem) throws PersistentException {
		try {
			PersistentSession session = cestoservice.CestoServicePersistentManager.instance().getSession();
			return loadPecaByORMID(session, IDItem);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Peca getPecaByORMID(int IDItem) throws PersistentException {
		try {
			PersistentSession session = cestoservice.CestoServicePersistentManager.instance().getSession();
			return getPecaByORMID(session, IDItem);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Peca loadPecaByORMID(int IDItem, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = cestoservice.CestoServicePersistentManager.instance().getSession();
			return loadPecaByORMID(session, IDItem, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Peca getPecaByORMID(int IDItem, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = cestoservice.CestoServicePersistentManager.instance().getSession();
			return getPecaByORMID(session, IDItem, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Peca loadPecaByORMID(PersistentSession session, int IDItem) throws PersistentException {
		try {
			return (Peca) session.load(cestoservice.Peca.class, Integer.valueOf(IDItem));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Peca getPecaByORMID(PersistentSession session, int IDItem) throws PersistentException {
		try {
			return (Peca) session.get(cestoservice.Peca.class, Integer.valueOf(IDItem));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Peca loadPecaByORMID(PersistentSession session, int IDItem, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Peca) session.load(cestoservice.Peca.class, Integer.valueOf(IDItem), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Peca getPecaByORMID(PersistentSession session, int IDItem, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Peca) session.get(cestoservice.Peca.class, Integer.valueOf(IDItem), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryPeca(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = cestoservice.CestoServicePersistentManager.instance().getSession();
			return queryPeca(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryPeca(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = cestoservice.CestoServicePersistentManager.instance().getSession();
			return queryPeca(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Peca[] listPecaByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = cestoservice.CestoServicePersistentManager.instance().getSession();
			return listPecaByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Peca[] listPecaByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = cestoservice.CestoServicePersistentManager.instance().getSession();
			return listPecaByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryPeca(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From cestoservice.Peca as Peca");
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
	
	public static List queryPeca(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From cestoservice.Peca as Peca");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Peca", lockMode);
			return query.list();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Peca[] listPecaByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryPeca(session, condition, orderBy);
			return (Peca[]) list.toArray(new Peca[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Peca[] listPecaByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			List list = queryPeca(session, condition, orderBy, lockMode);
			return (Peca[]) list.toArray(new Peca[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Peca loadPecaByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = cestoservice.CestoServicePersistentManager.instance().getSession();
			return loadPecaByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Peca loadPecaByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = cestoservice.CestoServicePersistentManager.instance().getSession();
			return loadPecaByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Peca loadPecaByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		Peca[] pecas = listPecaByQuery(session, condition, orderBy);
		if (pecas != null && pecas.length > 0)
			return pecas[0];
		else
			return null;
	}
	
	public static Peca loadPecaByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		Peca[] pecas = listPecaByQuery(session, condition, orderBy, lockMode);
		if (pecas != null && pecas.length > 0)
			return pecas[0];
		else
			return null;
	}
	
	public static java.util.Iterator iteratePecaByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = cestoservice.CestoServicePersistentManager.instance().getSession();
			return iteratePecaByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iteratePecaByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = cestoservice.CestoServicePersistentManager.instance().getSession();
			return iteratePecaByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iteratePecaByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From cestoservice.Peca as Peca");
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
	
	public static java.util.Iterator iteratePecaByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From cestoservice.Peca as Peca");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Peca", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Peca createPeca() {
		return new cestoservice.Peca();
	}
	
	public static boolean save(cestoservice.Peca peca) throws PersistentException {
		try {
			cestoservice.CestoServicePersistentManager.instance().saveObject(peca);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean delete(cestoservice.Peca peca) throws PersistentException {
		try {
			cestoservice.CestoServicePersistentManager.instance().deleteObject(peca);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean deleteAndDissociate(cestoservice.Peca peca)throws PersistentException {
		try {
			cestoservice.Set[] lSetss = (cestoservice.Set[])peca.getSets().toArray(new cestoservice.Set[peca.getSets().size()]);
			for(int i = 0; i < lSetss.length; i++) {
				lSetss[i].getPecas().remove(peca);
			}
			return delete(peca);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean deleteAndDissociate(cestoservice.Peca peca, org.orm.PersistentSession session)throws PersistentException {
		try {
			cestoservice.Set[] lSetss = (cestoservice.Set[])peca.getSets().toArray(new cestoservice.Set[peca.getSets().size()]);
			for(int i = 0; i < lSetss.length; i++) {
				lSetss[i].getPecas().remove(peca);
			}
			try {
				session.delete(peca);
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean refresh(cestoservice.Peca peca) throws PersistentException {
		try {
			cestoservice.CestoServicePersistentManager.instance().getSession().refresh(peca);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean evict(cestoservice.Peca peca) throws PersistentException {
		try {
			cestoservice.CestoServicePersistentManager.instance().getSession().evict(peca);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Peca loadPecaByCriteria(PecaCriteria pecaCriteria) {
		Peca[] pecas = listPecaByCriteria(pecaCriteria);
		if(pecas == null || pecas.length == 0) {
			return null;
		}
		return pecas[0];
	}
	
	public static Peca[] listPecaByCriteria(PecaCriteria pecaCriteria) {
		return pecaCriteria.listPeca();
	}
}
