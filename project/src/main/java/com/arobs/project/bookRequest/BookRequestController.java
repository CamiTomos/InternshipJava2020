package com.arobs.project.bookRequest;

import com.arobs.project.dtos.BookRequestDTO;
import com.arobs.project.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/library-app")
public class BookRequestController {
    private BookRequestService bookRequestService;

    @Autowired
    public BookRequestController(BookRequestService bookRequestService) {
        this.bookRequestService = bookRequestService;
    }

    @GetMapping(value = "/bookRequests")
    public ResponseEntity<?> handleGetAllBookRequests() {
        return new ResponseEntity<>(bookRequestService.getAllBookRequests(), HttpStatus.OK);
    }

    @PostMapping(value = "/bookRequests")
    public ResponseEntity<?> handleInsertBookRequest(@RequestBody BookRequestDTO bookRequestDTO) {
        try {
            return new ResponseEntity<>(bookRequestService.insertBookRequest(bookRequestDTO), HttpStatus.OK);
        } catch (ValidationException e) {
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
            return new ResponseEntity<>(updatedBook, HttpStatus.OK);
        } catch (ValidationException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/bookRequest/{id}")
    public ResponseEntity<?> handleDeleteBookRequest(@PathVariable int id) {
        boolean isBookDeleted = bookRequestService.deleteBookRequest(id);
        if (isBookDeleted) {
            return new ResponseEntity<>("Book was successfully deleted!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Book with given id does not exist!", HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/bookRequest/{id}")
    public ResponseEntity<?> handleFindBookRequestById(@PathVariable int id) {
        BookRequestDTO foundBook = bookRequestService.findBookRequestById(id);
        if (null == foundBook) {
            return new ResponseEntity<>("Book with given id does not exist!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(foundBook, HttpStatus.OK);
    }
}
