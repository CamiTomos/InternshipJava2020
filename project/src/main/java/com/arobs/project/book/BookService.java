package com.arobs.project.book;

import com.arobs.project.dtos.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("bookService")
public class BookService {
    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookDTO> getAllBooks(){
        List<Book> books=bookRepository.getAllBooks();
        List<BookDTO> bookDTOS=new ArrayList<>(books.size());
        for (Book book:books
             ) {
            bookDTOS.add(new BookDTO(book.getBookTitle(),book.getBookAuthor(),book.getBookDescription(),book.getBookAddedDate()));
        }
        return bookDTOS;
    }
}
