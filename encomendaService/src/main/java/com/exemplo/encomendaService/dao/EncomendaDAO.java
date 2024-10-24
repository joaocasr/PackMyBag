package main.java.com.exemplo.encomendaService.dao;

import javax.persistence.*;

import main.java.com.exemplo.encomendaService.model.Encomenda;

import java.util.List;

public class EncomendaDAO {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("nome_da_unidade_de_persistencia");

    public static Encomenda findEncomendaById(int idEncomenda) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Encomenda.class, idEncomenda);
        } finally {
            em.close();
        }
    }

    public static List<Encomenda> findAllEncomendas() {
        EntityManager em = emf.createEntityManager();
        try {
            String jpql = "SELECT e FROM Encomenda e";
            TypedQuery<Encomenda> query = em.createQuery(jpql, Encomenda.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public static List<Encomenda> findEncomendasByCondition(String condition) {
        EntityManager em = emf.createEntityManager();
        try {
            String jpql = "SELECT e FROM Encomenda e WHERE " + condition;
            TypedQuery<Encomenda> query = em.createQuery(jpql, Encomenda.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public static void saveEncomenda(Encomenda encomenda) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(encomenda);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new PersistenceException(e);
        } finally {
            em.close();
        }
    }

    public static void updateEncomenda(Encomenda encomenda) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(encomenda);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new PersistenceException(e);
        } finally {
            em.close();
        }
    }

    public static void deleteEncomenda(Encomenda encomenda) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.remove(em.contains(encomenda) ? encomenda : em.merge(encomenda));
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new PersistenceException(e);
        } finally {
            em.close();
        }
    }
}
