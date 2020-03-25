package com.arobs.project.bookRent;

import com.arobs.project.exception.ValidationException;

import java.text.ParseException;

public interface BookRentService {
    BookRent insertBookRent(BookRent bookRent) throws ParseException, ValidationException;

    void extendDeadlineBookRent(int id) throws ValidationException;

    void returnBook(BookRent bookRent) throws ValidationException;

    BookRent findBookRentById(int id) throws ValidationException;
}
