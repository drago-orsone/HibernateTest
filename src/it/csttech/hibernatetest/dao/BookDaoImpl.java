package it.csttech.hibernatetest.dao;

import java.util.List;

import org.hibernate.Session;

import it.csttech.hibernatetest.entities.Book;

public class BookDaoImpl implements BookDao<Book, Integer> {

	public BookDaoImpl() {
	}

	public void persist(Book entity, Session session) {
		session.save(entity);
	}
	
	public Book findById(Integer id, Session session){
		return (Book) session.get(Book.class, id);
	}
	
	public void delete(Book entity, Session session){
		session.delete(entity);
	}

	public void update(Book entity, Session session) {
		session.update(entity);
	}
	
	@SuppressWarnings("unchecked")
	public List<Book> findAll(Session session) {
		return (List<Book>) session.createQuery("from Book").list();
	}

}