package com.arobs.project.book;

import com.arobs.project.dtos.BookDTO;

import java.text.ParseException;
import java.util.List;

public interface BookService {

    List<BookDTO> getAllBooks();

    BookDTO insertBook(BookDTO bookDTO);

    boolean deleteBook(int id);

    BookDTO updateBook(BookDTO bookDTO);

    BookDTO findById(int id);
}
