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
import main.java.com.exemplo.encomendaService.model.Item;

import org.hibernate.Query;
import org.hibernate.LockMode;
import java.util.List;

public class ItemDAO {
	public static Item loadItemByORMID(int IDItem) throws PersistentException {
		try {
			PersistentSession session = main.java.com.exemplo.encomendaService.criteria.EncomendaServicePersistentManager.instance().getSession();
			return loadItemByORMID(session, IDItem);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Item getItemByORMID(int IDItem) throws PersistentException {
		try {
			PersistentSession session = main.java.com.exemplo.encomendaService.criteria.EncomendaServicePersistentManager.instance().getSession();
			return getItemByORMID(session, IDItem);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Item loadItemByORMID(int IDItem, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = main.java.com.exemplo.encomendaService.criteria.EncomendaServicePersistentManager.instance().getSession();
			return loadItemByORMID(session, IDItem, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Item getItemByORMID(int IDItem, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = main.java.com.exemplo.encomendaService.criteria.EncomendaServicePersistentManager.instance().getSession();
			return getItemByORMID(session, IDItem, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Item loadItemByORMID(PersistentSession session, int IDItem) throws PersistentException {
		try {
			return (Item) session.load(main.java.com.exemplo.encomendaService.model.Item.class, Integer.valueOf(IDItem));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Item getItemByORMID(PersistentSession session, int IDItem) throws PersistentException {
		try {
			return (Item) session.get(main.java.com.exemplo.encomendaService.model.Item.class, Integer.valueOf(IDItem));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Item loadItemByORMID(PersistentSession session, int IDItem, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Item) session.load(main.java.com.exemplo.encomendaService.model.Item.class, Integer.valueOf(IDItem), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Item getItemByORMID(PersistentSession session, int IDItem, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Item) session.get(main.java.com.exemplo.encomendaService.model.Item.class, Integer.valueOf(IDItem), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryItem(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = main.java.com.exemplo.encomendaService.criteria.EncomendaServicePersistentManager.instance().getSession();
			return queryItem(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryItem(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = main.java.com.exemplo.encomendaService.criteria.EncomendaServicePersistentManager.instance().getSession();
			return queryItem(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Item[] listItemByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = main.java.com.exemplo.encomendaService.criteria.EncomendaServicePersistentManager.instance().getSession();
			return listItemByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Item[] listItemByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = main.java.com.exemplo.encomendaService.criteria.EncomendaServicePersistentManager.instance().getSession();
			return listItemByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryItem(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From encomendaservice.Item as Item");
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
	
	public static List queryItem(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From encomendaservice.Item as Item");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Item", lockMode);
			return query.list();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Item[] listItemByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryItem(session, condition, orderBy);
			return (Item[]) list.toArray(new Item[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Item[] listItemByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			List list = queryItem(session, condition, orderBy, lockMode);
			return (Item[]) list.toArray(new Item[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Item loadItemByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = main.java.com.exemplo.encomendaService.criteria.EncomendaServicePersistentManager.instance().getSession();
			return loadItemByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Item loadItemByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = main.java.com.exemplo.encomendaService.criteria.EncomendaServicePersistentManager.instance().getSession();
			return loadItemByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Item loadItemByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		Item[] items = listItemByQuery(session, condition, orderBy);
		if (items != null && items.length > 0)
			return items[0];
		else
			return null;
	}
	
	public static Item loadItemByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		Item[] items = listItemByQuery(session, condition, orderBy, lockMode);
		if (items != null && items.length > 0)
			return items[0];
		else
			return null;
	}
	
	public static java.util.Iterator iterateItemByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = main.java.com.exemplo.encomendaService.criteria.EncomendaServicePersistentManager.instance().getSession();
			return iterateItemByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateItemByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = main.java.com.exemplo.encomendaService.criteria.EncomendaServicePersistentManager.instance().getSession();
			return iterateItemByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateItemByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From encomendaservice.Item as Item");
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
	
	public static java.util.Iterator iterateItemByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From encomendaservice.Item as Item");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Item", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Item createItem() {
		return new main.java.com.exemplo.encomendaService.model.Item();
	}
	
	public static boolean save(main.java.com.exemplo.encomendaService.model.Item item) throws PersistentException {
		try {
			main.java.com.exemplo.encomendaService.criteria.EncomendaServicePersistentManager.instance().saveObject(item);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean delete(main.java.com.exemplo.encomendaService.model.Item item) throws PersistentException {
		try {
			main.java.com.exemplo.encomendaService.criteria.EncomendaServicePersistentManager.instance().deleteObject(item);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean refresh(main.java.com.exemplo.encomendaService.model.Item item) throws PersistentException {
		try {
			main.java.com.exemplo.encomendaService.criteria.EncomendaServicePersistentManager.instance().getSession().refresh(item);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean evict(main.java.com.exemplo.encomendaService.model.Item item) throws PersistentException {
		try {
			main.java.com.exemplo.encomendaService.criteria.EncomendaServicePersistentManager.instance().getSession().evict(item);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Item loadItemByCriteria(ItemCriteria itemCriteria) {
		Item[] items = listItemByCriteria(itemCriteria);
		if(items == null || items.length == 0) {
			return null;
		}
		return items[0];
	}
	
	public static Item[] listItemByCriteria(ItemCriteria itemCriteria) {
		return itemCriteria.listItem();
	}
}
