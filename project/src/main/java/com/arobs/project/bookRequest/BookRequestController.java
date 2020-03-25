package com.arobs.project.bookRequest;

import com.arobs.project.dtos.BookRequestDTO;
import com.arobs.project.exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return new ResponseEntity<>(bookRequestService.findAllBookRequests(), HttpStatus.OK);
    }

    @PostMapping(value = "/bookRequests")
    public ResponseEntity<?> handleInsertBookRequest(@RequestBody BookRequestDTO bookRequestDTO) {
        try {
            log.info("Book request inserted!");
            return new ResponseEntity<>(bookRequestService.insertBookRequest(bookRequestDTO), HttpStatus.OK);
        } catch (ValidationException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/bookRequests")
    public ResponseEntity<?> handleUpdateBookRequest(@RequestBody BookRequestDTO bookRequestDTO) {
        try {
            BookRequestDTO updatedBook = bookRequestService.updateBookRequest(bookRequestDTO);
            if (null == updatedBook) {
                return new ResponseEntity<>("BookRequest with given id does not exist!", HttpStatus.NOT_FOUND);
            }
            log.info("Book request updated!");
            return new ResponseEntity<>(updatedBook, HttpStatus.OK);
        } catch (ValidationException ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/bookRequest/{id}")
    public ResponseEntity<?> handleDeleteBookRequest(@PathVariable int id) {
        boolean isBookDeleted = bookRequestService.deleteBookRequest(id);
        if (isBookDeleted) {
            log.info("Book was successfully deleted!");
            return new ResponseEntity<>("Book was successfully deleted!", HttpStatus.OK);
        }
        log.error("Book with given id does not exist!");
        return new ResponseEntity<>("Book with given id does not exist!", HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/bookRequest/{id}")
    public ResponseEntity<?> handleFindBookRequestById(@PathVariable int id) {
        BookRequestDTO foundBook = bookRequestService.findBookRequestById(id);
        if (null == foundBook) {
            log.error("Book with given id does not exist!");
            return new ResponseEntity<>("Book with given id does not exist!", HttpStatus.NOT_FOUND);
        }
        log.info("Book request found!");
        return new ResponseEntity<>(foundBook, HttpStatus.OK);
    }
}
