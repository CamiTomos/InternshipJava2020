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
        session.save(tag);
        return tag;
    }

    public Tag getTagByDescription(String description) {
        Session session = sessionFactory.getCurrentSession();
        List<Tag> tags = session.createQuery("from Tag T where T.tagDescription= :description")
                .setParameter("description", description)
                .getResultList();
        if (tags.size() == 1) {
            return tags.get(0);
        }
        return null;
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
