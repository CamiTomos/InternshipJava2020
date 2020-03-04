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
    public ResponseEntity<?> handleGetAllCopies() {
        return new ResponseEntity<>(copyService.getAllCopies(), HttpStatus.OK);
    }

    @GetMapping(value = "/copies/{id}")
    public ResponseEntity<?> handleFindCopyById(@PathVariable int id) {
        CopyDTO foundCopy = copyService.findCopyById(id);
        if (foundCopy != null) {
            return new ResponseEntity<>(foundCopy, HttpStatus.OK);
        }
        return new ResponseEntity<>("The copy with the given id does not exist!", HttpStatus.BAD_REQUEST);
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
        CopyDTO updatedCopy = copyService.updateCopy(copyDTO);
        if (updatedCopy != null) {
            return new ResponseEntity<>(updatedCopy, HttpStatus.OK);
        }
        return new ResponseEntity<>("The copy could not be updated!", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/copies/{id}")
    public ResponseEntity<?> handleDeleteCopy(@PathVariable int id) {
        boolean isCopyDeleted = copyService.deleteCopy(id);
        if (isCopyDeleted == true) {
            return new ResponseEntity<>("Copy deleted successfully!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Copy was not deleted!", HttpStatus.BAD_REQUEST);
    }
}
