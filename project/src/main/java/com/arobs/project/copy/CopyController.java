package com.arobs.project.copy;

import com.arobs.project.dtos.CopyDTO;
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

@RestController("copyController")
@RequestMapping("/library-app")
public class CopyController {
    private CopyService copyService;
    private final Logger log = LoggerFactory.getLogger("FILE");

    @Autowired
    public CopyController(CopyService copyService) {
        this.copyService = copyService;
    }

    @GetMapping(value = "/copies")
    public ResponseEntity<?> handleFindAllCopies() {
        log.info("Copies found successfully!");
        List<CopyDTO> foundCopies = copyService.findAllCopies()
                .stream()
                .map(ProjectModelMapper::convertCopyToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(foundCopies, HttpStatus.OK);
    }

    @GetMapping(value = "/copies/{id}")
    public ResponseEntity<?> handleFindCopyById(@PathVariable int id) {
        try {
            log.info("Copy found!");
            CopyDTO foundCopy = ProjectModelMapper.convertCopyToDTO(copyService.findCopyById(id));
            return new ResponseEntity<>(foundCopy, HttpStatus.OK);
        } catch (ValidationException ex) {
            log.info(ex.getMessage());
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/copies")
    public ResponseEntity<?> handleInsertCopy(@RequestBody CopyDTO copyDTO) {
        try {
            log.info("Copy inserted!");
            Copy copyToInsert = ProjectModelMapper.convertDTOtoCopy(copyDTO);
            return new ResponseEntity<>(ProjectModelMapper.convertCopyToDTO(copyService.insertCopy(copyToInsert)), HttpStatus.OK);
        } catch (ValidationException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/copies")
    public ResponseEntity<?> handleUpdateCopy(@RequestBody CopyDTO copyDTO) {
        try {
            log.info("Copy updated!");
            Copy copyToUpdate = ProjectModelMapper.convertDTOtoCopy(copyDTO);
            return new ResponseEntity<>(ProjectModelMapper.convertCopyToDTO(copyService.updateCopy(copyToUpdate)), HttpStatus.OK);
        } catch (ValidationException ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/copies/{id}")
    public ResponseEntity<?> handleDeleteCopy(@PathVariable int id) {
        boolean isCopyDeleted = copyService.deleteCopy(id);
        if (isCopyDeleted) {
            log.info("Copy deleted successfully!");
            return new ResponseEntity<>("Copy deleted successfully!", HttpStatus.OK);
        }
        log.error("Copy was not deleted!");
        return new ResponseEntity<>("Copy was not deleted!", HttpStatus.BAD_REQUEST);
    }
}
