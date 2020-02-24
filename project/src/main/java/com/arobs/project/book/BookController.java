package com.arobs.project.book;

import com.arobs.project.dtos.BookDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

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
    List<BookDTO> handleGetAllBooks() {
        log.info("BookController: get all books...");
        return bookService.getAllBooks();
    }

    @PostMapping(value = "/books", produces = "application/json")
    ResponseEntity handleInsertBook(@RequestBody BookDTO bookDTO) {
        try {
            return new ResponseEntity(bookService.insertBook(bookDTO), HttpStatus.OK);

        } catch (ParseException ex) {
            return new ResponseEntity("Date is not valid", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/books")
    ResponseEntity handleUpdateBook(@RequestBody BookDTO bookDTO) {
        try {
            return new ResponseEntity(bookService.updateBook(bookDTO), HttpStatus.OK);

        } catch (ParseException ex) {
            return new ResponseEntity("Date is not valid", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/books/{id}")
    public ResponseEntity<String> handleDeleteBook(@PathVariable int id) {
        if (bookService.deleteBook(id)) {
            return new ResponseEntity("Book was deleted successfully!", HttpStatus.OK);
        } else return new ResponseEntity("Book was not deleted!", HttpStatus.BAD_REQUEST);
    }
}
