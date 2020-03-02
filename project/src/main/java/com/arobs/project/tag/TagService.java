package com.arobs.project.tag;

import com.arobs.project.dtos.TagDTO;

public interface TagService {
    TagDTO insertTag(TagDTO tagDTO);
    TagDTO findTagByDescription(String description);
    TagDTO findTagById(int id);
    boolean deleteTag(int id);
}
