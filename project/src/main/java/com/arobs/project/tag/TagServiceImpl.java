package com.arobs.project.tag;

import com.arobs.project.dtos.TagDTO;
import com.arobs.project.mappers.ProjectModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@Service("tagServiceImpl")
@EnableTransactionManagement
public class TagServiceImpl implements TagService{
    private TagHibernateRepository hibernateRepository;

    @Autowired
    public TagServiceImpl(TagHibernateRepository hibernateRepository) {
        this.hibernateRepository = hibernateRepository;
    }


    @Override
    @Transactional
    public TagDTO insertTag(TagDTO tagDTO) {
        Tag tag = ProjectModelMapper.convertDTOtoTag(tagDTO);
        return ProjectModelMapper.convertTagToDTO(hibernateRepository.insertTag(tag));
    }

    @Override
    @Transactional
    public TagDTO findTagByDescription(String description) {
        return ProjectModelMapper.convertTagToDTO(hibernateRepository.getTagByDescription(description));
    }

    @Override
    @Transactional
    public TagDTO findTagById(int id) {
        return ProjectModelMapper.convertTagToDTO(hibernateRepository.findById(id));
    }

    @Override
    @Transactional
    public boolean deleteTag(int id) {
        return false;
    }
}
