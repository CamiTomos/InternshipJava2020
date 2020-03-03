package com.arobs.project.tag;

import com.arobs.project.dtos.TagDTO;

import java.util.List;

public interface TagService {
    List<TagDTO> getAllTags();

    TagDTO insertTag(TagDTO tagDTO);

    TagDTO findTagByDescription(String description);

    TagDTO findTagById(int id);

    boolean deleteTag(int id);

    TagDTO updateTag(TagDTO tag);
}
