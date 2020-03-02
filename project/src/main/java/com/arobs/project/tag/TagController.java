package com.arobs.project.tag;

import com.arobs.project.dtos.EmployeeDTO;
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

    @GetMapping(value = "/{description}")
    public ResponseEntity<?> handleFindByDescription(@PathVariable String description){
        return new ResponseEntity<>(service.findTagByDescription(description), HttpStatus.OK);
    }



}
