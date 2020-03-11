package com.arobs.project.bookRequest;

import com.arobs.project.dtos.BookRequestDTO;
import com.arobs.project.exception.ValidationException;

import java.util.List;

public interface BookRequestService {
    List<BookRequestDTO> findAllBookRequests();

    BookRequestDTO insertBookRequest(BookRequestDTO bookRequestDTO) throws ValidationException;

    boolean deleteBookRequest(int id);

    BookRequestDTO updateBookRequest(BookRequestDTO bookRequestDTO) throws ValidationException;

    BookRequestDTO findBookRequestById(int id);
}
