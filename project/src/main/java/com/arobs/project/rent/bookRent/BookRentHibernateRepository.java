package com.arobs.project.rent.bookRent;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository("bookRentHibernateRepository")
public class BookRentHibernateRepository {
    private SessionFactory sessionFactory;
    private final Logger log = LoggerFactory.getLogger("FILE");

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

    public List<BookRent> markRentalsLate(Timestamp currentTime) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "select b from BookRent b where b.bookrentReturnDate< :currentTime and b.bookrentStatus='on_going'";
        Query selectQuery = session.createQuery(hql);
        selectQuery.setParameter("currentTime", currentTime);
        List<BookRent> lateRentals = selectQuery.getResultList();
        log.info("There are {} late rentals", lateRentals.size());
        return lateRentals;
    }
}
