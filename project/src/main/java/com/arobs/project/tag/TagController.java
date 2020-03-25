package com.arobs.project.tag;

import com.arobs.project.dtos.TagDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return new ResponseEntity<>(service.insertTag(tagDTO), HttpStatus.OK);
    }

    @GetMapping(value = "/tags", produces = "application/json")
    public ResponseEntity<?> handleFindAllTags() {
        log.info("Tags found!");
        return new ResponseEntity<>(service.findAllTags(), HttpStatus.OK);
    }

    @GetMapping(value = "/tags/description")
    public ResponseEntity<?> handleFindByDescription(@RequestParam(value = "description") String description) {
        TagDTO foundTag = service.findTagByDescription(description);
        if (foundTag == null) {
            log.error("Sorry! There is no tag with given description!");
            return new ResponseEntity<>("Sorry! There is no tag with given description!", HttpStatus.NOT_FOUND);
        }
        log.info("Tag with {} description found!", description);
        return new ResponseEntity<>(foundTag, HttpStatus.OK);
    }

    @GetMapping(value = "/tags/{id}")
    public ResponseEntity<?> handleFindTagById(@PathVariable int id) {
        TagDTO foundTag = service.findTagById(id);
        if (foundTag == null) {
            log.error("Sorry! There is no tag with given id!");
            return new ResponseEntity<>("Sorry! There is no tag with given id!", HttpStatus.NOT_FOUND);
        }
        log.info("Tag found!");
        return new ResponseEntity<>(foundTag, HttpStatus.OK);
    }

    @PutMapping(value = "/tags")
    public ResponseEntity<?> handleUpdateTag(@RequestBody TagDTO tagDTO) {
        TagDTO updatedTag = service.updateTag(tagDTO);
        if (updatedTag == null) {
            log.error("Sorry! This tag can not be updated!");
            return new ResponseEntity<>("Sorry! This tag can not be updated!", HttpStatus.BAD_REQUEST);
        }
        log.info("Tag updated!");
        return new ResponseEntity<>(updatedTag, HttpStatus.OK);
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
