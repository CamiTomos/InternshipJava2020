package com.arobs.project.tag;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("tagHibernateRepository")
public class TagHibernateRepository {
    private SessionFactory sessionFactory;

    @Autowired
    public TagHibernateRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Tag> findAllTags() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Tag").getResultList();
    }

    public Tag insertTag(Tag tag) {
        Session session = sessionFactory.getCurrentSession();
        int id = (int) session.save(tag);
        tag.setId(id);
        return tag;
    }

    public List<Tag> getTagsByDescription(String description) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Tag T where T.tagDescription= :description")
                .setParameter("description", description)
                .getResultList();
    }

    public boolean deleteTag(Tag tag) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(tag);
        return true;
    }

    public Tag updateTag(Tag tag) {
        Session session = sessionFactory.getCurrentSession();
        Tag foundTag = session.get(Tag.class, tag.getId());
        foundTag.setTagDescription(tag.getTagDescription());
        return foundTag;
    }

    public Tag findTagById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Tag.class, id);
    }
}
