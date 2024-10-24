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
import main.java.com.exemplo.encomendaService.dto.ClienteDTO;
import main.java.com.exemplo.encomendaService.mapper.ClienteMapper;
import main.java.com.exemplo.encomendaService.model.Cliente;

import org.hibernate.Query;
import org.hibernate.LockMode;
import java.util.List;

public class ClienteDAO {
	/*
	// Exemplo de método que retorna um DTO em vez de uma entidade diretamente
	public ClienteDTO getClienteDTOByORMID(int IDCliente) throws PersistentException {
		Cliente cliente = ClienteDAO.getClienteByORMID(IDCliente);
		if (cliente != null) {
			return ClienteMapper.toDTO(cliente);  // Converte a entidade em DTO
		}
		return null;
	}
	*/

	public ClienteDTO getClienteDTOByORMID(int IDCliente) throws PersistentException {
        Cliente cliente = ClienteDAO.getClienteByORMID(IDCliente);
        return cliente != null ? ClienteMapper.toDTO(cliente) : null;
    }


	// Método que converte Cliente para ClienteDTO
	private ClienteDTO toDTO(Cliente cliente) {
		ClienteDTO dto = new ClienteDTO();
		dto.setIDCliente(cliente.getIDCliente());
		dto.setNome(cliente.getNome());
		dto.setUsername(cliente.getUsername());
		dto.setEmail(cliente.getEmail());
		return dto;
	}

	public static Cliente loadClienteByORMID(int IDCliente) throws PersistentException {
		try {
			PersistentSession session = main.java.com.exemplo.encomendaService.criteria.EncomendaServicePersistentManager.instance().getSession();
			return loadClienteByORMID(session, IDCliente);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Cliente getClienteByORMID(int IDCliente) throws PersistentException {
		try {
			PersistentSession session = main.java.com.exemplo.encomendaService.criteria.EncomendaServicePersistentManager.instance().getSession();
			return getClienteByORMID(session, IDCliente);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Cliente loadClienteByORMID(int IDCliente, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = main.java.com.exemplo.encomendaService.criteria.EncomendaServicePersistentManager.instance().getSession();
			return loadClienteByORMID(session, IDCliente, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Cliente getClienteByORMID(int IDCliente, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = main.java.com.exemplo.encomendaService.criteria.EncomendaServicePersistentManager.instance().getSession();
			return getClienteByORMID(session, IDCliente, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Cliente loadClienteByORMID(PersistentSession session, int IDCliente) throws PersistentException {
		try {
			return (Cliente) session.load(main.java.com.exemplo.encomendaService.model.Cliente.class, Integer.valueOf(IDCliente));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Cliente getClienteByORMID(PersistentSession session, int IDCliente) throws PersistentException {
		try {
			return (Cliente) session.get(main.java.com.exemplo.encomendaService.model.Cliente.class, Integer.valueOf(IDCliente));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Cliente loadClienteByORMID(PersistentSession session, int IDCliente, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Cliente) session.load(main.java.com.exemplo.encomendaService.model.Cliente.class, Integer.valueOf(IDCliente), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Cliente getClienteByORMID(PersistentSession session, int IDCliente, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Cliente) session.get(main.java.com.exemplo.encomendaService.model.Cliente.class, Integer.valueOf(IDCliente), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryCliente(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = main.java.com.exemplo.encomendaService.criteria.EncomendaServicePersistentManager.instance().getSession();
			return queryCliente(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryCliente(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = main.java.com.exemplo.encomendaService.criteria.EncomendaServicePersistentManager.instance().getSession();
			return queryCliente(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Cliente[] listClienteByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = main.java.com.exemplo.encomendaService.criteria.EncomendaServicePersistentManager.instance().getSession();
			return listClienteByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Cliente[] listClienteByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = main.java.com.exemplo.encomendaService.criteria.EncomendaServicePersistentManager.instance().getSession();
			return listClienteByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryCliente(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From encomendaservice.Cliente as Cliente");
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
	
	public static List queryCliente(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From encomendaservice.Cliente as Cliente");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Cliente", lockMode);
			return query.list();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Cliente[] listClienteByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryCliente(session, condition, orderBy);
			return (Cliente[]) list.toArray(new Cliente[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Cliente[] listClienteByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			List list = queryCliente(session, condition, orderBy, lockMode);
			return (Cliente[]) list.toArray(new Cliente[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Cliente loadClienteByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = main.java.com.exemplo.encomendaService.criteria.EncomendaServicePersistentManager.instance().getSession();
			return loadClienteByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Cliente loadClienteByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = main.java.com.exemplo.encomendaService.criteria.EncomendaServicePersistentManager.instance().getSession();
			return loadClienteByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Cliente loadClienteByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		Cliente[] clientes = listClienteByQuery(session, condition, orderBy);
		if (clientes != null && clientes.length > 0)
			return clientes[0];
		else
			return null;
	}
	
	public static Cliente loadClienteByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		Cliente[] clientes = listClienteByQuery(session, condition, orderBy, lockMode);
		if (clientes != null && clientes.length > 0)
			return clientes[0];
		else
			return null;
	}
	
	public static java.util.Iterator iterateClienteByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = main.java.com.exemplo.encomendaService.criteria.EncomendaServicePersistentManager.instance().getSession();
			return iterateClienteByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateClienteByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = main.java.com.exemplo.encomendaService.criteria.EncomendaServicePersistentManager.instance().getSession();
			return iterateClienteByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateClienteByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From encomendaservice.Cliente as Cliente");
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
	
	public static java.util.Iterator iterateClienteByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From encomendaservice.Cliente as Cliente");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Cliente", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Cliente createCliente() {
		return new main.java.com.exemplo.encomendaService.model.Cliente();
	}
	
	public static boolean save(main.java.com.exemplo.encomendaService.model.Cliente cliente) throws PersistentException {
		try {
			main.java.com.exemplo.encomendaService.criteria.EncomendaServicePersistentManager.instance().saveObject(cliente);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean delete(main.java.com.exemplo.encomendaService.model.Cliente cliente) throws PersistentException {
		try {
			main.java.com.exemplo.encomendaService.criteria.EncomendaServicePersistentManager.instance().deleteObject(cliente);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean refresh(main.java.com.exemplo.encomendaService.model.Cliente cliente) throws PersistentException {
		try {
			main.java.com.exemplo.encomendaService.criteria.EncomendaServicePersistentManager.instance().getSession().refresh(cliente);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean evict(main.java.com.exemplo.encomendaService.model.Cliente cliente) throws PersistentException {
		try {
			main.java.com.exemplo.encomendaService.criteria.EncomendaServicePersistentManager.instance().getSession().evict(cliente);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Cliente loadClienteByCriteria(ClienteCriteria clienteCriteria) {
		Cliente[] clientes = listClienteByCriteria(clienteCriteria);
		if(clientes == null || clientes.length == 0) {
			return null;
		}
		return clientes[0];
	}
	
	public static Cliente[] listClienteByCriteria(ClienteCriteria clienteCriteria) {
		return clienteCriteria.listCliente();
	}
}
