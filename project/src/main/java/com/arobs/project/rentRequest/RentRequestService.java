package com.arobs.project.rentRequest;

import com.arobs.project.dtos.RentRequestDTO;
import com.arobs.project.exception.ValidationException;

public interface RentRequestService {
    void insertRentRequest(RentRequestDTO rentRequestDTO) throws ValidationException;

    void acceptRentRequest(int id) throws ValidationException;

    void declineRentRequest(int id) throws ValidationException;

    boolean findRequestByBook(int bookId);
}
