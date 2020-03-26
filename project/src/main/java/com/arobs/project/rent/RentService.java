package com.arobs.project.rent;

import com.arobs.project.exception.ValidationException;

public interface RentService {
    String insertBookRent(int employeeId, int bookId) throws ValidationException;

    void returnBook(int id, double grade) throws ValidationException;

    void acceptRentRequest(int id) throws ValidationException;

    void declineRentRequest(int id) throws ValidationException;

    void insertRentRequest(int employeeId, int bookId) throws ValidationException;

    void extendDeadlineBookRent(int id) throws ValidationException;

    void markRentalsLate();

    void checkEmailConfirmation();
}
