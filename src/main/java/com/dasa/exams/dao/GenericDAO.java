package com.dasa.exams.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

public class GenericDAO<T> implements Serializable {
	
	/**
	 * This is a Generic class of DAO, it must be used to create a others daos easily
	 */
	private static final long serialVersionUID = 5449410005002768429L;

	private final Class<T> clazz;
	private EntityManager em;

	public GenericDAO(EntityManager em, Class<T> clazz) {
		this.clazz = clazz;
		this.em = em;
	}
	
	public void insert (T t){
		em.persist(t);
	}
	
	public void remove(T t){
		em.remove(em.merge(t));
	}
	
	public void update(T t){
		em.merge(t);
	}
	
	public List<T> getAll(){
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(clazz);
		query.select(query.from(clazz));
		
		List<T> list = em.createQuery(query).getResultList();
		
		return list;
	}
	
	public T findById(long id){
		T instance = em.find(clazz, id);
		return instance;
	}
	
	public T findByName(String name) {
		T instance = em.find(clazz, name);
		return instance;
	}


}
