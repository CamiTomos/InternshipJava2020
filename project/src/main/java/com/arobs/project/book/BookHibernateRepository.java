package com.arobs.project.book;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository("bookHibernateRepository")
public class BookHibernateRepository {
    private SessionFactory sessionFactory;
    private final Logger log = LoggerFactory.getLogger(BookHibernateRepository.class);

    @Autowired
    public BookHibernateRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Book insertBook(Book book) {
        log.info("Insert book...");
        Session session = sessionFactory.getCurrentSession();
        book.setBookAddedDate(new Timestamp(System.currentTimeMillis()));
        session.save(book);
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
        return session.get(Book.class, id);
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
        return session.createQuery("from Book", Book.class).getResultList();
    }
}
