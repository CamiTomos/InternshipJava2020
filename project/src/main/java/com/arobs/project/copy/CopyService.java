package com.arobs.project.copy;

import com.arobs.project.exception.ValidationException;

import java.util.List;

public interface CopyService {
    List<Copy> findAllCopies();

    Copy insertCopy(Copy copy) throws ValidationException;

    boolean deleteCopy(int id);

    Copy updateCopy(Copy copy) throws ValidationException;

    Copy findCopyById(int id) throws ValidationException;

    List<Copy> findAvailableCopiesForBook(int bookId);

    List<Copy> findPendingCopiesForBook(int bookId);
}
