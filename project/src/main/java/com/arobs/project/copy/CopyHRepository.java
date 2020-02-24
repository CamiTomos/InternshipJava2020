package com.arobs.project.copy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository("copyHRepository")
public class CopyHRepository {
    EntityManager entityManager;

    @Autowired
    public CopyHRepository(EntityManager entityManager){
        this.entityManager=entityManager;
    }

    public List<Copy> getAllCopies(){
        Session session = this.entityManager.unwrap(Session.class);
        String hql = "select * from copies";
        return session.createQuery(hql).list();
    }


}
