package com.arobs.project.book;

import com.arobs.project.tag.TagService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository("bookHibernateRepository")
public class BookHibernateRepository {
    private SessionFactory sessionFactory;

    @Autowired
    public BookHibernateRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Book insertBook(Book book) {
        Session session = sessionFactory.getCurrentSession();
        book.setBookAddedDate(new Timestamp(System.currentTimeMillis()));
        session.save(book);
        return book;
    }

    public boolean deleteBook(Book book){
        Session session=sessionFactory.getCurrentSession();
        session.delete(book);
        return true;
    }

    public Book findById(int id){
        Session session=sessionFactory.getCurrentSession();
        return session.get(Book.class,id);
    }
}
