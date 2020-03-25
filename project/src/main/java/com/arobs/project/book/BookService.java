package com.arobs.project.book;

import com.arobs.project.exception.ValidationException;

import java.util.List;

public interface BookService {

    List<Book> findAllBooks();

    Book insertBook(Book book) throws ValidationException;

    boolean deleteBook(int id);

    Book updateBook(Book book) throws ValidationException;

    Book findBookById(int id) throws ValidationException;
}
