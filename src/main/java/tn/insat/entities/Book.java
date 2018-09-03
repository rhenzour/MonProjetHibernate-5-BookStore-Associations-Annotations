package tn.insat.entities;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
/**
 * Books generated by hbm2java
 */
@Entity
@Table(name="books"
    ,catalog="bookstoredb"
)
@NamedQuery(
	    name="bookCount",
	    query = "select b.title , count (a)  from Author a inner join a.books b where a.country = :country group by b "
	)
public class Book  implements java.io.Serializable {

    /**
	 * Generated Serial Version
	 */
	 private static final long serialVersionUID = -4646580628570085708L;
	
	 @Id 
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 @Column(name="b_book_sk", unique=true, nullable=false)
     private Integer bookId;
	 
	 @ManyToOne(fetch=FetchType.LAZY)
	    @JoinColumns( { 
	        @JoinColumn(name="b_publisher_fk", referencedColumnName="p_publisher_sk", nullable=true) } )
	 private Publisher publisher;
	 
	 @Column(name="b_title", length=450)
     private String title;
	 
	 @Column(name="b_pages")
     private Integer pages;
	 
	 @ManyToMany(fetch=FetchType.EAGER)
	    @JoinTable(name="book_author", catalog="bookstoredb", joinColumns = { 
	        @JoinColumn(name="ba_book_fk", nullable=true, updatable=true) }, inverseJoinColumns = { 
	        @JoinColumn(name="ba_author_fk", nullable=true, updatable=true) })
     private Set<Author> authors = new HashSet<Author>(0);

    public Book() {
    }




	public Book(Integer bookId, Publisher publisher, String title,
			Integer pages, Set<Author> authors) {
		super();
		this.bookId = bookId;
		this.publisher = publisher;
		this.title = title;
		this.pages = pages;
		this.authors = authors;
	}




	public Book(Publisher publisher, String title, Integer pages,
			Set<Author> authors) {
		super();
		this.publisher = publisher;
		this.title = title;
		this.pages = pages;
		this.authors = authors;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

	public Set<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}

}


