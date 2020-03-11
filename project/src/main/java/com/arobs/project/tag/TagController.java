package com.arobs.project.tag;

import com.arobs.project.dtos.TagDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/library-app")
public class TagController {
    private TagService service;

    @Autowired
    public TagController(TagService service) {
        this.service = service;
    }

    @PostMapping(value = "/tags")
    public ResponseEntity<?> handleInsertTag(@RequestBody TagDTO tagDTO) {
        return new ResponseEntity<>(service.insertTag(tagDTO), HttpStatus.OK);
    }

    @GetMapping(value = "/tags", produces = "application/json")
    public ResponseEntity<?> handleFindAllTags() {
        return new ResponseEntity<>(service.findAllTags(), HttpStatus.OK);
    }

    @GetMapping(value = "/tags/description")
    public ResponseEntity<?> handleFindByDescription(@RequestParam(value = "description") String description) {
        TagDTO foundTag = service.findTagByDescription(description);
        if (foundTag == null) {
            return new ResponseEntity<>("Sorry! There is no tag with given description!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(foundTag, HttpStatus.OK);
    }

    @GetMapping(value = "/tags/{id}")
    public ResponseEntity<?> handleFindById(@PathVariable int id) {
        TagDTO foundTag = service.findTagById(id);
        if (foundTag == null) {
            return new ResponseEntity<>("Sorry! There is no tag with given id!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(foundTag, HttpStatus.OK);
    }

    @PutMapping(value = "/tags")
    public ResponseEntity<?> handleUpdateTag(@RequestBody TagDTO tagDTO) {
        TagDTO updatedTag = service.updateTag(tagDTO);
        if (updatedTag == null) {
            return new ResponseEntity<>("Sorry! This tag can not be updated!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(updatedTag, HttpStatus.OK);
    }

    @DeleteMapping(value = "/tags/{id}")
    public ResponseEntity<?> handleDeleteTag(@PathVariable int id) {
        boolean isDeleted = service.deleteTag(id);
        if (!isDeleted) {
            return new ResponseEntity<>("Sorry! This tag does not exist!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Tag successfully deleted!", HttpStatus.OK);
    }


}
