package com.arobs.project.tag;

import com.arobs.project.dtos.TagDTO;
import com.arobs.project.exception.ValidationException;

import java.util.List;

public interface TagService {
    List<Tag> findAllTags();

    Tag insertTag(Tag tag);

    Tag findTagByDescription(String description) throws ValidationException;

    Tag findTagById(int id) throws ValidationException;

    boolean deleteTag(int id);

    Tag updateTag(Tag tag) throws ValidationException;
}
