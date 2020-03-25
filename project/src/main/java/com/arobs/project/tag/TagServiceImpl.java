package com.arobs.project.tag;

import com.arobs.project.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("tagServiceImpl")
@EnableTransactionManagement
public class TagServiceImpl implements TagService {
    private TagHibernateRepository hibernateRepository;

    @Autowired
    public TagServiceImpl(TagHibernateRepository hibernateRepository) {
        this.hibernateRepository = hibernateRepository;
    }

    @Override
    @Transactional
    public List<Tag> findAllTags() {
        return hibernateRepository.findAllTags();
    }

    @Override
    @Transactional
    public Tag insertTag(Tag tag) {
        return hibernateRepository.insertTag(tag);
    }

    @Override
    @Transactional
    public Tag findTagByDescription(String description) throws ValidationException {
        List<Tag> foundTags = hibernateRepository.getTagsByDescription(description);
        if (foundTags.isEmpty()) {
            throw new ValidationException("Sorry! There is no tag with given description!");
        }
        return foundTags.get(0);
    }

    @Override
    @Transactional
    public Tag findTagById(int id) throws ValidationException {
        Tag foundTag = hibernateRepository.findTagById(id);
        if (null == foundTag) {
            throw new ValidationException("Sorry! There is no tag with given id!");
        }
        return foundTag;
    }

    @Override
    @Transactional
    public boolean deleteTag(int id) {
        Tag foundTag = hibernateRepository.findTagById(id);
        if (null == foundTag) {
            return false;
        }
        hibernateRepository.deleteTag(foundTag);
        return true;
    }

    @Override
    @Transactional
    public Tag updateTag(Tag tag) throws ValidationException {
        Tag foundTag = hibernateRepository.findTagById(tag.getId());
        if (null == foundTag) {
            throw new ValidationException("Sorry! This tag can not be updated!");
        }
        return hibernateRepository.updateTag(tag);
    }
}
