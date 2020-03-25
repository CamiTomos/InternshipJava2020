package com.arobs.project.rentRequest;

import com.arobs.project.exception.ValidationException;

import java.util.List;

public interface RentRequestService {
    void insertRentRequest(int employeeId, int bookId) throws ValidationException;

    void acceptRentRequest(RentRequest rentRequest) throws ValidationException;

    RentRequest getRentRequestById(int id) throws ValidationException;

    void declineRentRequest(RentRequest rentRequest) throws ValidationException;

    List<RentRequest> findWaitingAvailableCopiesRequests(int bookId);

    void sendEmail(RentRequest selectedRequest);
}
