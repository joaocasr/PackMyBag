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

public class SetDAO {
	public static Set loadSetByORMID(int IDItem) throws PersistentException {
		try {
			PersistentSession session = cestoservice.CestoServicePersistentManager.instance().getSession();
			return loadSetByORMID(session, IDItem);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Set getSetByORMID(int IDItem) throws PersistentException {
		try {
			PersistentSession session = cestoservice.CestoServicePersistentManager.instance().getSession();
			return getSetByORMID(session, IDItem);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Set loadSetByORMID(int IDItem, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = cestoservice.CestoServicePersistentManager.instance().getSession();
			return loadSetByORMID(session, IDItem, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Set getSetByORMID(int IDItem, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = cestoservice.CestoServicePersistentManager.instance().getSession();
			return getSetByORMID(session, IDItem, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Set loadSetByORMID(PersistentSession session, int IDItem) throws PersistentException {
		try {
			return (Set) session.load(cestoservice.Set.class, Integer.valueOf(IDItem));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Set getSetByORMID(PersistentSession session, int IDItem) throws PersistentException {
		try {
			return (Set) session.get(cestoservice.Set.class, Integer.valueOf(IDItem));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Set loadSetByORMID(PersistentSession session, int IDItem, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Set) session.load(cestoservice.Set.class, Integer.valueOf(IDItem), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Set getSetByORMID(PersistentSession session, int IDItem, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Set) session.get(cestoservice.Set.class, Integer.valueOf(IDItem), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List querySet(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = cestoservice.CestoServicePersistentManager.instance().getSession();
			return querySet(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List querySet(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = cestoservice.CestoServicePersistentManager.instance().getSession();
			return querySet(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Set[] listSetByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = cestoservice.CestoServicePersistentManager.instance().getSession();
			return listSetByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Set[] listSetByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = cestoservice.CestoServicePersistentManager.instance().getSession();
			return listSetByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List querySet(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From cestoservice.Set as Set");
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
	
	public static List querySet(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From cestoservice.Set as Set");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Set", lockMode);
			return query.list();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Set[] listSetByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = querySet(session, condition, orderBy);
			return (Set[]) list.toArray(new Set[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Set[] listSetByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			List list = querySet(session, condition, orderBy, lockMode);
			return (Set[]) list.toArray(new Set[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Set loadSetByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = cestoservice.CestoServicePersistentManager.instance().getSession();
			return loadSetByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Set loadSetByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = cestoservice.CestoServicePersistentManager.instance().getSession();
			return loadSetByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Set loadSetByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		Set[] sets = listSetByQuery(session, condition, orderBy);
		if (sets != null && sets.length > 0)
			return sets[0];
		else
			return null;
	}
	
	public static Set loadSetByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		Set[] sets = listSetByQuery(session, condition, orderBy, lockMode);
		if (sets != null && sets.length > 0)
			return sets[0];
		else
			return null;
	}
	
	public static java.util.Iterator iterateSetByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = cestoservice.CestoServicePersistentManager.instance().getSession();
			return iterateSetByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateSetByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = cestoservice.CestoServicePersistentManager.instance().getSession();
			return iterateSetByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateSetByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From cestoservice.Set as Set");
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
	
	public static java.util.Iterator iterateSetByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From cestoservice.Set as Set");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Set", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Set createSet() {
		return new cestoservice.Set();
	}
	
	public static boolean save(cestoservice.Set set) throws PersistentException {
		try {
			cestoservice.CestoServicePersistentManager.instance().saveObject(set);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean delete(cestoservice.Set set) throws PersistentException {
		try {
			cestoservice.CestoServicePersistentManager.instance().deleteObject(set);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean deleteAndDissociate(cestoservice.Set set)throws PersistentException {
		try {
			cestoservice.Peca[] lPecass = (cestoservice.Peca[])set.getPecas().toArray(new cestoservice.Peca[set.getPecas().size()]);
			for(int i = 0; i < lPecass.length; i++) {
				lPecass[i].getSets().remove(set);
			}
			return delete(set);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean deleteAndDissociate(cestoservice.Set set, org.orm.PersistentSession session)throws PersistentException {
		try {
			cestoservice.Peca[] lPecass = (cestoservice.Peca[])set.getPecas().toArray(new cestoservice.Peca[set.getPecas().size()]);
			for(int i = 0; i < lPecass.length; i++) {
				lPecass[i].getSets().remove(set);
			}
			try {
				session.delete(set);
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
	
	public static boolean refresh(cestoservice.Set set) throws PersistentException {
		try {
			cestoservice.CestoServicePersistentManager.instance().getSession().refresh(set);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean evict(cestoservice.Set set) throws PersistentException {
		try {
			cestoservice.CestoServicePersistentManager.instance().getSession().evict(set);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Set loadSetByCriteria(SetCriteria setCriteria) {
		Set[] sets = listSetByCriteria(setCriteria);
		if(sets == null || sets.length == 0) {
			return null;
		}
		return sets[0];
	}
	
	public static Set[] listSetByCriteria(SetCriteria setCriteria) {
		return setCriteria.listSet();
	}
}
