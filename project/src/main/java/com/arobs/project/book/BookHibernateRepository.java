package com.arobs.project.book;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository("bookHibernateRepository")
public class BookHibernateRepository {
    private SessionFactory sessionFactory;
    private final Logger log = LoggerFactory.getLogger("FILE");

    @Autowired
    public BookHibernateRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Book insertBook(Book book) {
        log.info("Insert book...");
        Session session = sessionFactory.getCurrentSession();
        book.setBookAddedDate(new Timestamp(System.currentTimeMillis()));
        int id = (int) session.save(book);
        book.setId(id);
        return book;
    }

    public boolean deleteBook(Book book) {
        log.info("Delete book...");
        Session session = sessionFactory.getCurrentSession();
        session.delete(book);
        return true;
    }

    public Book findBookById(int id) {
        log.info("Find book by id...");
        Session session = sessionFactory.getCurrentSession();
        String hqlFind = "select distinct b from Book b join fetch b.tags where b.id = :id";
        Query queryFind = session.createQuery(hqlFind);
        queryFind.setParameter("id", id);
        List<Book> foundBooks = queryFind.getResultList();
        if (foundBooks.isEmpty()) {
            return null;
        }
        return foundBooks.get(0);
    }

    public Book updateBook(Book book) {
        log.info("Update book...");
        Session session = sessionFactory.getCurrentSession();
        Book foundBook = session.get(Book.class, book.getId());
        foundBook.setBookTitle(book.getBookTitle());
        foundBook.setBookAuthor(book.getBookAuthor());
        foundBook.setBookDescription(book.getBookDescription());
        foundBook.setTags(book.getTags());
        return book;
    }

    public List<Book> findAllBooks() {
        log.info("Find all books...");
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select distinct b from Book b join fetch b.tags", Book.class).getResultList();
    }
}
