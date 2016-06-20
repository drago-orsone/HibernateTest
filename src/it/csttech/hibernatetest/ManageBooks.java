package it.csttech.hibernatetest;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import it.csttech.hibernatetest.entities.Book;
import it.csttech.hibernatetest.service.BookServiceImpl;
import it.csttech.hibernatetest.service.BookService;

public class ManageBooks {

	public ManageBooks( SessionFactory sessionFactory ) {
		BookService<Book, Integer> bookService = new BookServiceImpl(sessionFactory);
		Book book1 = new Book("The Brothers Karamazov", "Fyodor Dostoevsky");
		Book book2 = new Book("War and Peace", "Leo Tolstoy");
        Book book3 = new Book("Pride and Prejudice", "Jane Austen");

		bookService.persist(book1);
		bookService.persist(book2);
		bookService.persist(book3);
		
		book1.setTitle("Crime and Punishment");
		bookService.update(book1);
		
		bookService.delete(book3);
		bookService.delete(bookService.findById(book2.getId()));
		
	}

	public static void main(String[] args) {
		
		SessionFactory sessionFactory = null;
		try {
			sessionFactory = new Configuration().configure(new java.io.File("cfg/hibernate.cfg.xml"))
					.setInterceptor(new LoggingInterceptor()).buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}

		new ManageBooks(sessionFactory);
		sessionFactory.close();
		System.out.println("Done!!");
	}

}
