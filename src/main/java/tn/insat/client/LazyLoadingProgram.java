package tn.insat.client;

import java.util.HashSet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import tn.insat.entities.Author;
import tn.insat.entities.Book;
import tn.insat.entities.Publisher;


@SuppressWarnings({ "rawtypes", "unchecked" })
public class LazyLoadingProgram {

	public static void main(String[] args) {

		// Getting Session Factory
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		// Getting Session
		Session session = sessionFactory.openSession();
		// Beginning Transaction
		Transaction tx = session.beginTransaction();

		Author aut1 = new Author(101, "Martin FOWLER", "USA", new HashSet(0));
		Author aut2 = new Author(102, "Kent BECK", "USA", new HashSet(0));
		Author aut3 = new Author(103, "John BRANT", "USA", new HashSet(0));
		Author aut4 = new Author(104, "William OPDYKE", "USA", new HashSet(0));
		Author aut5 = new Author(105, "Don ROBERTS ", "USA", new HashSet(0));

		Publisher p = new Publisher("Addison Wesley", new HashSet(0));

		Book b = new Book(101, p,"Refactoring: Improving the Design of Existing Code ", 464,new HashSet(0));

		b.getAuthors().add(aut1);
		b.getAuthors().add(aut2);
		b.getAuthors().add(aut3);
		b.getAuthors().add(aut4);
		b.getAuthors().add(aut5);

		b.setPublisher(p);

		// Saving entities
		session.save(aut1);
		session.save(aut2);
		session.save(aut3);
		session.save(aut4);
		session.save(aut5);

		session.save(p);
		session.save(b);

		// Committing and closing session
		tx.commit();
		session.close();
		
		
		// Reopening Session for querying
		session = sessionFactory.openSession();
		// Getting Book By ID
		Book br = (Book) session.get(Book.class, 1);
		//Book br = (Book) session.load(Book.class, 101);
		
		// Closing Session
		session.close();
		sessionFactory.close();
		// Displaying Title and Number of Authors (Dependency)
		System.out.println(br.getTitle());
		System.out.println(br.getAuthors().size());

	}
}
