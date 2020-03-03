package com.arobs.project.tag;

import com.arobs.project.tag.Tag;
import com.arobs.project.dtos.TagDTO;
import com.arobs.project.dtos.TagDTO;
import com.arobs.project.mappers.ProjectModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    public List<TagDTO> getAllTags() {
        List<Tag> tags = hibernateRepository.getAllTags();
        List<TagDTO> tagDTOS = new ArrayList<>(tags.size());
        for (Tag tag : tags) {
            tagDTOS.add(ProjectModelMapper.convertTagToDTO(tag));
        }
        return tagDTOS;
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
        Tag foundTag = hibernateRepository.getTagByDescription(description);
        if (foundTag != null) {
            return ProjectModelMapper.convertTagToDTO(foundTag);
        }
        return null;
    }

    @Override
    @Transactional
    public TagDTO findTagById(int id) {
        Tag foundTag = hibernateRepository.findTagById(id);
        if (foundTag != null) {
            return ProjectModelMapper.convertTagToDTO(foundTag);
        }
        return null;
    }

    @Override
    @Transactional
    public boolean deleteTag(int id) {
        Tag foundTag = hibernateRepository.findTagById(id);
        if (foundTag != null) {
            hibernateRepository.deleteTag(foundTag);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public TagDTO updateTag(TagDTO tagDTO) {
        Tag foundTag = hibernateRepository.findTagById(tagDTO.getId());
        if (foundTag != null) {
            Tag tag=ProjectModelMapper.convertDTOtoTag(tagDTO);
            return ProjectModelMapper.convertTagToDTO(hibernateRepository.updateTag(tag));
        }
        return null;
    }


}
