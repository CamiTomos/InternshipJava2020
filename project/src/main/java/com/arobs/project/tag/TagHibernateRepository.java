package com.arobs.project.tag;

import com.arobs.project.tag.Tag;
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

    public List<Tag> getAllTags() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Tag").getResultList();
    }

    public Tag insertTag(Tag tag) {
        Session session = sessionFactory.getCurrentSession();
        session.save(tag);
        return tag;
    }

    public Tag getTagByDescription(String description) {
//        refactor this
        Session session = sessionFactory.getCurrentSession();
        List<Tag> tags = session.createQuery("from Tag").getResultList();
        for (Tag tag : tags) {
            if (tag.getTagDescription().equals(description)) {
                return tag;
            }
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
