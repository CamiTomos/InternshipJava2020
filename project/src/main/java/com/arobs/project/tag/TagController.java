package com.arobs.project.tag;

import com.arobs.project.dtos.TagDTO;
import com.arobs.project.exception.ValidationException;
import com.arobs.project.mappers.ProjectModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/library-app")
public class TagController {
    private TagService service;
    private final Logger log = LoggerFactory.getLogger("FILE");

    @Autowired
    public TagController(TagService service) {
        this.service = service;
    }

    @PostMapping(value = "/tags")
    public ResponseEntity<?> handleInsertTag(@RequestBody TagDTO tagDTO) {
        log.info("Tag inserted!");
        Tag tag = ProjectModelMapper.convertDTOtoTag(tagDTO);
        return new ResponseEntity<>(ProjectModelMapper.convertTagToDTO(service.insertTag(tag)), HttpStatus.OK);
    }

    @GetMapping(value = "/tags", produces = "application/json")
    public ResponseEntity<?> handleFindAllTags() {
        log.info("Tags found!");
        List<TagDTO> foundTags = service.findAllTags()
                .stream()
                .map(ProjectModelMapper::convertTagToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(foundTags, HttpStatus.OK);
    }

    @GetMapping(value = "/tags/description")
    public ResponseEntity<?> handleFindByDescription(@RequestParam(value = "description") String description) {
        try {
            TagDTO foundTag = ProjectModelMapper.convertTagToDTO(service.findTagByDescription(description));
            log.info("Tag with {} description found!", description);
            return new ResponseEntity<>(foundTag, HttpStatus.OK);
        } catch (ValidationException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/tags/{id}")
    public ResponseEntity<?> handleFindTagById(@PathVariable int id) {
        try {
            TagDTO foundTag = ProjectModelMapper.convertTagToDTO(service.findTagById(id));
            log.info("Tag found!");
            return new ResponseEntity<>(foundTag, HttpStatus.OK);
        } catch (ValidationException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/tags")
    public ResponseEntity<?> handleUpdateTag(@RequestBody TagDTO tagDTO) {
        try {
            Tag mappedTag = ProjectModelMapper.convertDTOtoTag(tagDTO);
            TagDTO updatedTag = ProjectModelMapper.convertTagToDTO(service.updateTag(mappedTag));
            log.info("Tag updated!");
            return new ResponseEntity<>(updatedTag, HttpStatus.OK);
        } catch (ValidationException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/tags/{id}")
    public ResponseEntity<?> handleDeleteTag(@PathVariable int id) {
        boolean isDeleted = service.deleteTag(id);
        if (!isDeleted) {
            log.error("Sorry! This tag does not exist!");
            return new ResponseEntity<>("Sorry! This tag does not exist!", HttpStatus.BAD_REQUEST);
        }
        log.info("Tag successfully deleted!");
        return new ResponseEntity<>("Tag successfully deleted!", HttpStatus.OK);
    }
}
