package com.arobs.project.copy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("copyHibernateRepository")
public class CopyHibernateRepository {
    SessionFactory sessionFactory;

    @Autowired
    public CopyHibernateRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Copy> findAllCopies() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Copy").getResultList();
    }

    public Copy insertCopy(Copy copy) {
        Session session = sessionFactory.getCurrentSession();
        session.save(copy);
        return copy;
    }

    public Copy updateCopy(Copy copy) {
        Session session = sessionFactory.getCurrentSession();
        Copy foundCopy = session.get(Copy.class, copy.getId());
        foundCopy.setCopyFlag(copy.getCopyFlag());
        foundCopy.setCopyStatus(copy.getCopyStatus());
        foundCopy.setBook(copy.getBook());
        return foundCopy;
    }

    public boolean deleteCopy(Copy copy) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(copy);
        return true;
    }

    public Copy findCopyById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Copy.class, id);
    }

    public List<Copy> findAvailableCopiesForBook(int bookId) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select c from Copy c inner join c.book where c.book.id= :id and c.copyStatus='AVAILABLE' and c.copyFlag=false")
                .setParameter("id", bookId)
                .getResultList();
    }

    public List<Copy> findPendingCopiesForBook(int bookId) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select c from Copy c inner join c.book where c.book.id= :id and c.copyStatus='PENDING'")
                .setParameter("id", bookId)
                .getResultList();
    }
}
