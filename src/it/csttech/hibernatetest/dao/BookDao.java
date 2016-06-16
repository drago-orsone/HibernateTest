package it.csttech.hibernatetest.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
//import java.util.List;

public interface BookDao<T, Id extends Serializable> {

	public void persist(T entity, Session session);
	
	public void update(T entity, Session session);
	
	public T findById(Id id, Session session);
	
	public void delete(T entity, Session session);
	
	public List<T> findAll(Session session);
	
}