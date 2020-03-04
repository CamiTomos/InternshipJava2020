package com.arobs.project.copy;

import com.arobs.project.dtos.CopyDTO;
import com.arobs.project.exception.ValidationException;

import java.util.List;

public interface CopyService {
    List<CopyDTO> getAllCopies();

    CopyDTO insertCopy(CopyDTO copyDTO) throws ValidationException;

    boolean deleteCopy(int id);

    CopyDTO updateCopy(CopyDTO copyDTO);

    CopyDTO findCopyById(int id);
}
