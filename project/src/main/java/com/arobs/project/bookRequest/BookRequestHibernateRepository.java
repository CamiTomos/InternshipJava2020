package com.arobs.project.bookRequest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("bookRequestHibernateRepository")
public class BookRequestHibernateRepository {
    private SessionFactory sessionFactory;

    @Autowired
    public BookRequestHibernateRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<BookRequest> findAllBookRequests() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from BookRequest").getResultList();
    }

    public BookRequest insertBookRequest(BookRequest bookRequest) {
        Session session = sessionFactory.getCurrentSession();
        session.save(bookRequest);
        return bookRequest;
    }

    public BookRequest updateBookRequest(BookRequest bookRequest) {
        Session session = sessionFactory.getCurrentSession();
        BookRequest foundBookRequest = session.get(BookRequest.class, bookRequest.getId());
        foundBookRequest.setBookrequestAuthor(bookRequest.getBookrequestAuthor());
        foundBookRequest.setBookrequestCopiesNeeded(bookRequest.getBookrequestCopiesNeeded());
        foundBookRequest.setBookrequestLink(bookRequest.getBookrequestLink());
        foundBookRequest.setBookrequestPublishingCompany(bookRequest.getBookrequestPublishingCompany());
        foundBookRequest.setBookrequestStatus(bookRequest.getBookrequestStatus());
        foundBookRequest.setBookrequestTitle(bookRequest.getBookrequestTitle());
        foundBookRequest.setBookrequestTotalCost(bookRequest.getBookrequestTotalCost());
        foundBookRequest.setEmployee(bookRequest.getEmployee());
        return foundBookRequest;
    }

    public boolean deleteBookRequest(BookRequest bookRequest) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(bookRequest);
        return true;
    }

    public BookRequest findBookRequestById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(BookRequest.class, id);
    }
}
