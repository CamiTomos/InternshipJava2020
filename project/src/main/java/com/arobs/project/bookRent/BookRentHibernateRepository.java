package com.arobs.project.bookRent;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("bookRentHibernateRepository")
public class BookRentHibernateRepository {
    private SessionFactory sessionFactory;

    @Autowired
    public BookRentHibernateRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public BookRent insertBookRent(BookRent bookRent) {
        Session session = sessionFactory.getCurrentSession();
        session.save(bookRent);
        return bookRent;
    }

    public List<BookRent> getAllBookRents() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from BookRent").getResultList();
    }

    public BookRent updateBookRent(BookRent bookRent) {
        Session session = sessionFactory.getCurrentSession();
        session.update(bookRent);
        return bookRent;
    }

    public BookRent getBookRentById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(BookRent.class, id);
    }
}
