package com.arobs.project.copy;

import com.arobs.project.dtos.CopyDTO;
import com.arobs.project.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("copyController")
@RequestMapping("/library-app")
public class CopyController {
    private CopyService copyService;

    @Autowired
    public CopyController(CopyService copyService) {
        this.copyService = copyService;
    }

    @GetMapping(value = "/copies")
    public ResponseEntity<?> handleFindAllCopies() {
        return new ResponseEntity<>(copyService.findAllCopies(), HttpStatus.OK);
    }

    @GetMapping(value = "/copies/{id}")
    public ResponseEntity<?> handleFindCopyById(@PathVariable int id) {
        try {
            return new ResponseEntity<>(copyService.findCopyById(id), HttpStatus.OK);
        } catch (ValidationException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/copies")
    public ResponseEntity<?> handleInsertCopy(@RequestBody CopyDTO copyDTO) {
        try {
            return new ResponseEntity<>(copyService.insertCopy(copyDTO), HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/copies")
    public ResponseEntity<?> handleUpdateCopy(@RequestBody CopyDTO copyDTO) {
        try {
            return new ResponseEntity<>(copyService.updateCopy(copyDTO), HttpStatus.OK);
        } catch (ValidationException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/copies/{id}")
    public ResponseEntity<?> handleDeleteCopy(@PathVariable int id) {
        boolean isCopyDeleted = copyService.deleteCopy(id);
        if (isCopyDeleted) {
            return new ResponseEntity<>("Copy deleted successfully!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Copy was not deleted!", HttpStatus.BAD_REQUEST);
    }
}
