package com.arobs.project.book;

import com.arobs.project.dtos.BookDTO;
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
@RequestMapping("/library-app")
public class BookController {
    private BookServiceImpl bookService;
    private final Logger log = LoggerFactory.getLogger("FILE");

    @Autowired
    public BookController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    @GetMapping(value = "/books", produces = "application/json")
    public ResponseEntity<?> handleFindAllBooks() {
        log.info("BookController: get all books...");
        List<BookDTO> foundBooks = bookService.findAllBooks()
                .stream()
                .map(ProjectModelMapper::convertBookToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(foundBooks, HttpStatus.OK);
    }

    @GetMapping(value = "/books/{id}")
    public ResponseEntity<?> handleFindBookById(@NotBlank @PathVariable int id) {
        try {
            log.info("Book successfully found!");
            BookDTO foundBook = ProjectModelMapper.convertBookToDTO(bookService.findBookById(id));
            return new ResponseEntity<>(foundBook, HttpStatus.OK);
        } catch (ValidationException ex) {
            log.error("Book could not be found!");
            return new ResponseEntity<>("Book with given id does not exist!", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Server exception!");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/books", produces = "application/json")
    public ResponseEntity<?> handleInsertBook(@Valid @RequestBody BookDTO bookDTO) {
        log.info("Book inserted!");
        Book book = ProjectModelMapper.convertDTOtoBook(bookDTO);
        return new ResponseEntity<>(ProjectModelMapper.convertBookToDTO(bookService.insertBook(book)), HttpStatus.OK);
    }

    @PutMapping(value = "/books")
    public ResponseEntity<?> handleUpdateBook(@Valid @RequestBody BookDTO bookDTO) {
        try {
            Book mappedBook = ProjectModelMapper.convertDTOtoBook(bookDTO);
            Book updatedBook = bookService.updateBook(mappedBook);
            log.info("Book successfully updated!");
            return new ResponseEntity<>(ProjectModelMapper.convertBookToDTO(updatedBook), HttpStatus.OK);
        } catch (ValidationException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Server exception!");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/books/{id}")
    public ResponseEntity<String> handleDeleteBook(@NotBlank @PathVariable int id) {
        if (bookService.deleteBook(id)) {
            log.info("Book deleted!");
            return new ResponseEntity<>("Book was deleted successfully!", HttpStatus.OK);
        } else {
            log.error("Book could not be deleted!");
            return new ResponseEntity<>("Book was not deleted!", HttpStatus.BAD_REQUEST);
        }
    }
}
