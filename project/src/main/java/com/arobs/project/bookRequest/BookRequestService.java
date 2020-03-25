package com.arobs.project.bookRequest;

import com.arobs.project.exception.ValidationException;

import java.util.List;

public interface BookRequestService {
    List<BookRequest> findAllBookRequests();

    BookRequest insertBookRequest(BookRequest bookRequest) throws ValidationException;

    boolean deleteBookRequest(int id);

    BookRequest updateBookRequest(BookRequest bookRequest) throws ValidationException;

    BookRequest findBookRequestById(int id) throws ValidationException;
}
