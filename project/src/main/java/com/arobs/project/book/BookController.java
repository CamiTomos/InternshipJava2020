package com.arobs.project.book;

import com.arobs.project.dtos.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/library-app")
public class BookController {
    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(value = "/books")
    List<BookDTO> handleGetAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping(value = "/books")
    void handleInsertBook(@RequestBody BookDTO bookDTO){
        bookService.insertBook(bookDTO);
    }

    @PutMapping(value = "/books/{id}")
    BookDTO handleUpdateBook(@RequestBody BookDTO bookDTO, @PathVariable int id){
        return bookService.updateBook(bookDTO,id);
    }

    @DeleteMapping(value = "/books/{id}")
    void handleDeleteBook(@PathVariable int id){
        bookService.deleteBook(id);
    }
}
