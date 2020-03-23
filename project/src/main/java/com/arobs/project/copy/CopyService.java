package com.arobs.project.copy;

import com.arobs.project.dtos.CopyDTO;
import com.arobs.project.exception.ValidationException;

import java.util.List;

public interface CopyService {
    List<CopyDTO> findAllCopies();

    CopyDTO insertCopy(CopyDTO copyDTO) throws ValidationException;

    boolean deleteCopy(int id);

    CopyDTO updateCopy(CopyDTO copyDTO) throws ValidationException;

    CopyDTO findCopyById(int id) throws ValidationException;

    List<CopyDTO> findAvailableCopiesForBook(int bookId);
    List<CopyDTO> findPendingCopiesForBook(int bookId);
    List<CopyDTO> findRentedCopiesForBook(int bookId);
}
