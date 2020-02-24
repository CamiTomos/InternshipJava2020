package com.arobs.project.book;

import com.arobs.project.dtos.BookDTO;

import java.text.ParseException;
import java.util.List;

public interface BookService {

    List<BookDTO> getAllBooks();

    BookDTO insertBook(BookDTO bookDTO) throws ParseException;

    boolean deleteBook(int id);

    BookDTO updateBook(BookDTO bookDTO) throws ParseException;

    BookDTO findById(int id);
}
