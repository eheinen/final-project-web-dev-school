package com.fiap.school.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;

public class GenericDao<T> implements Dao<T> {

	private final Class<T> classEntity;
	protected EntityManager em;

	public GenericDao(Class<T> classEntity) {
		this.classEntity = classEntity;
	}

	@Override
	public void insert(T entity) {
		try {
			em = JpaUtil.getEntityManager();
			em.getTransaction().begin();
			em.persist(entity);
			em.getTransaction().commit();
			em.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (em.isOpen())
				em.close();
		}
	}

	@Override
	public void update(T entity) {
		try {
			em = JpaUtil.getEntityManager();
			em.getTransaction().begin();
			em.merge(entity);
			em.getTransaction().commit();
			em.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (em.isOpen())
				em.close();
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> list() {
		try {
			em = JpaUtil.getEntityManager();
			List<T> entities = em.createQuery("From " + classEntity.getSimpleName()).getResultList();

			return entities;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (em.isOpen())
				em.close();
		}

		return null;
	}

	@Override
	public T search(int id) {
		try {
			em = JpaUtil.getEntityManager();
			em.getTransaction().begin();
			T entity = em.find(classEntity, id);
			em.getTransaction().commit();
			em.close();

			return entity;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (em.isOpen())
				em.close();
		}
		return null;
	}

	@Override
	public void delete(int id) {
		try {
			em = JpaUtil.getEntityManager();
			em.getTransaction().begin();
			T entity = em.find(classEntity, id);
			em.remove(entity);
			em.getTransaction().commit();
			em.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (em.isOpen())
				em.close();
		}
	}

}
