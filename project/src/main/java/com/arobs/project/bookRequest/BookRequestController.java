package com.arobs.project.bookRequest;

import com.arobs.project.dtos.BookRequestDTO;
import com.arobs.project.exception.ValidationException;
import com.arobs.project.mappers.ProjectModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping(value = "/library-app")
public class BookRequestController {
    private BookRequestService bookRequestService;
    private final Logger log = LoggerFactory.getLogger("FILE");

    @Autowired
    public BookRequestController(BookRequestService bookRequestService) {
        this.bookRequestService = bookRequestService;
    }

    @GetMapping(value = "/bookRequests")
    public ResponseEntity<?> handleFindAllBookRequests() {
        log.info("Book requests found successfully!");
        List<BookRequestDTO> foundBookRequests = bookRequestService.findAllBookRequests()
                .stream()
                .map(ProjectModelMapper::convertBookRequestToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(foundBookRequests, HttpStatus.OK);
    }

    @PostMapping(value = "/bookRequests")
    public ResponseEntity<?> handleInsertBookRequest(@Valid @RequestBody BookRequestDTO bookRequestDTO) {
        try {
            log.info("Book request inserted!");
            BookRequest bookRequestToInsert = ProjectModelMapper.convertDTOtoBookRequest(bookRequestDTO);
            BookRequest bookRequestToBeReturned = bookRequestService.insertBookRequest(bookRequestToInsert);
            return new ResponseEntity<>(ProjectModelMapper.convertBookRequestToDTO(bookRequestToBeReturned), HttpStatus.OK);
        } catch (ValidationException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Server exception!");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/bookRequests")
    public ResponseEntity<?> handleUpdateBookRequest(@Valid @RequestBody BookRequestDTO bookRequestDTO) {
        try {
            BookRequest bookRequestToBeUpdated = ProjectModelMapper.convertDTOtoBookRequest(bookRequestDTO);
            BookRequestDTO updatedBookRequest = ProjectModelMapper.convertBookRequestToDTO(bookRequestService.updateBookRequest(bookRequestToBeUpdated));
            log.info("Book request updated!");
            return new ResponseEntity<>(updatedBookRequest, HttpStatus.OK);
        } catch (ValidationException ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Server exception!");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/bookRequest/{id}")
    public ResponseEntity<?> handleDeleteBookRequest(@NotBlank @PathVariable int id) {
        boolean isBookDeleted = bookRequestService.deleteBookRequest(id);
        if (isBookDeleted) {
            log.info("Book was successfully deleted!");
            return new ResponseEntity<>("Book was successfully deleted!", HttpStatus.OK);
        }
        log.error("Book with given id does not exist!");
        return new ResponseEntity<>("Book with given id does not exist!", HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/bookRequest/{id}")
    public ResponseEntity<?> handleFindBookRequestById(@NotBlank @PathVariable int id) {
        try {
            BookRequestDTO foundBook = ProjectModelMapper.convertBookRequestToDTO(bookRequestService.findBookRequestById(id));
            log.info("Book request found!");
            return new ResponseEntity<>(foundBook, HttpStatus.OK);
        } catch (ValidationException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Server exception!");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
