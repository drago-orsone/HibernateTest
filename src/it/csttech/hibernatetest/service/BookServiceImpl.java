package it.csttech.hibernatetest.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import it.csttech.hibernatetest.dao.BookDao;
import it.csttech.hibernatetest.dao.BookDaoImpl;
import it.csttech.hibernatetest.entities.Book;

public class BookServiceImpl implements BookService<Book, Integer> {

	private BookDao<Book, Integer> bookDao;
	
	private SessionFactory sessionFactory;

	public BookServiceImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		bookDao = new BookDaoImpl();
	}
	
	//@Transactional (Spring)
	public void persist(Book entity) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		bookDao.persist(entity, session);
		tx.commit();
		session.flush();
		session.close();
	}

	public Book findById(Integer id) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Book book = bookDao.findById(id, session);
		tx.commit();
		session.flush();
		session.close();
		return book;
	}
	
	public void delete(Book entity) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		bookDao.delete(entity, session);
		tx.commit();
		session.flush();
		session.close();
	}
	
	public void update(Book entity) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		bookDao.update(entity, session);
		tx.commit();
		session.flush();
		session.close();
	}
	
	public List<Book> findAll() {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<Book> books = bookDao.findAll(session);
		tx.commit();
		session.flush();
		session.close();
		return books;
	}
	
	public void deleteAll() {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<Book> entityList = bookDao.findAll(session);
		for (Book entity : entityList)
			bookDao.delete(entity, session);
		tx.commit();
		session.flush();
		session.close();
	}
}	