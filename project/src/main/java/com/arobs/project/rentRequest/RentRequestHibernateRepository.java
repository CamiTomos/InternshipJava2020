package com.arobs.project.rentRequest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
