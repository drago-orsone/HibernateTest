package it.csttech.hibernatetest.service;

import java.util.List;

import org.hibernate.HibernateException;
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

	// @Transactional (Spring)
	public void persist(Book entity) {
		sessionManager(entity, (Book book, Session session) ->  bookDao.persist(book, session));
	}
	
	public void delete(Book entity) {
		sessionManager(entity, (Book book, Session session) ->  bookDao.delete(book, session));
	}
	
	public void update(Book entity) {
		sessionManager(entity, (Book book, Session session) ->  bookDao.update(book, session));
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

	interface ActionPerformed {
		public void doAction(Book entity, Session session);
	}

	private void sessionManager(Book entity, ActionPerformed actionPerformed) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			actionPerformed.doAction(entity, session);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
	}


}