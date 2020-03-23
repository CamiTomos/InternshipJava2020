package com.arobs.project.rentRequest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("rentRequestHibernateRepository")
public class RentRequestHibernateRepository {
    private SessionFactory sessionFactory;

    @Autowired
    public RentRequestHibernateRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public RentRequest insertRentRequest(RentRequest rentRequest) {
        Session session = sessionFactory.getCurrentSession();
        session.save(rentRequest);
        return rentRequest;
    }

    public RentRequest getRentRequestById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(RentRequest.class, id);
    }

    public RentRequest updateRentRequest(RentRequest rentRequest) {
        Session session = sessionFactory.getCurrentSession();
        session.update(rentRequest);
        return rentRequest;
    }

    public List<RentRequest> findWaitingAvailableCopiesRequests(int bookId) {
        Session session = sessionFactory.getCurrentSession();
        String hqlFindRequests = "select r from RentRequest r " +
                "where r.book.id = :id and r.rentrequestStatus='waiting_available'";
        Query queryFindRequests = session.createQuery(hqlFindRequests);
        queryFindRequests.setParameter("id", bookId);
        return queryFindRequests.getResultList();
    }

    public List<RentRequest> findWaitingConfirmationRequests() {
        Session session = sessionFactory.getCurrentSession();
        String hqlFindRequests = "select r from RentRequest r " +
                "where r.rentrequestStatus='waiting_confirmation'";
        return session.createQuery(hqlFindRequests).getResultList();
    }
}
