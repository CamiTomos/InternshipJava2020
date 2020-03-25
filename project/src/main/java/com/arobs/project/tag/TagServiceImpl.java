package com.arobs.project.tag;

import com.arobs.project.dtos.TagDTO;
import com.arobs.project.mappers.ProjectModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<TagDTO> findAllTags() {
        return hibernateRepository.findAllTags()
                .stream()
                .map(ProjectModelMapper::convertTagToDTO)
                .collect(Collectors.toList());
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
        List<Tag> foundTags = hibernateRepository.getTagsByDescription(description);
        if (foundTags.size() == 1) {
            return ProjectModelMapper.convertTagToDTO(foundTags.get(0));
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
            Tag tag = ProjectModelMapper.convertDTOtoTag(tagDTO);
            return ProjectModelMapper.convertTagToDTO(hibernateRepository.updateTag(tag));
        }
        return null;
    }
}
