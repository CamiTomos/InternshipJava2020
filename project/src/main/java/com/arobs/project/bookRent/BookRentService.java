package com.arobs.project.bookRent;

import com.arobs.project.dtos.BookRentDTO;
import com.arobs.project.exception.ValidationException;

import java.text.ParseException;

public interface BookRentService {
    BookRentDTO insertBookRent(BookRentDTO bookRentDTO) throws ParseException, ValidationException;
}
