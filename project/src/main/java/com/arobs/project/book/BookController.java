package com.arobs.project.book;

import com.arobs.project.dtos.BookDTO;
import com.arobs.project.exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/library-app")
public class BookController {
    private BookServiceImpl bookService;
    private final Logger log = LoggerFactory.getLogger(BookController.class);

    @Autowired
    public BookController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    @GetMapping(value = "/books", produces = "application/json")
    public ResponseEntity<?> handleFindAllBooks() {
        log.info("BookController: get all books...");
        return new ResponseEntity<>(bookService.findAllBooks(), HttpStatus.OK);
    }

    @GetMapping(value = "/books/{id}")
    public ResponseEntity<?> handleFindBookById(@PathVariable int id) {
        try {
            return new ResponseEntity<>(bookService.findById(id), HttpStatus.OK);
        } catch (ValidationException ex) {
            return new ResponseEntity<>("Book with given id does not exist!", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/books", produces = "application/json")
    public ResponseEntity<?> handleInsertBook(@RequestBody BookDTO bookDTO) {
        return new ResponseEntity<>(bookService.insertBook(bookDTO), HttpStatus.OK);
    }

    @PutMapping(value = "/books")
    public ResponseEntity<?> handleUpdateBook(@RequestBody BookDTO bookDTO) {
        BookDTO updatedBook = bookService.updateBook(bookDTO);
        if (null != updatedBook) {
            return new ResponseEntity<>(updatedBook, HttpStatus.OK);
        }
        return new ResponseEntity<>("Book can not be updated!", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/books/{id}")
    public ResponseEntity<String> handleDeleteBook(@PathVariable int id) {
        if (bookService.deleteBook(id)) {
            return new ResponseEntity<>("Book was deleted successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Book was not deleted!", HttpStatus.BAD_REQUEST);
        }
    }

}
