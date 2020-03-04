package com.arobs.project.copy;

import com.arobs.project.book.BookService;
import com.arobs.project.dtos.BookDTO;
import com.arobs.project.dtos.CopyDTO;
import com.arobs.project.enums.CopyStatus;
import com.arobs.project.exception.ValidationException;
import com.arobs.project.mappers.ProjectModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("copyServiceImpl")
@EnableTransactionManagement
public class CopyServiceImpl implements CopyService {
    private CopyHibernateRepository hibernateRepository;
    private BookService bookService;

    @Autowired
    public CopyServiceImpl(CopyHibernateRepository hibernateRepository, BookService bookService) {
        this.hibernateRepository = hibernateRepository;
        this.bookService = bookService;
    }

    @Override
    @Transactional
    public List<CopyDTO> getAllCopies() {
        List<Copy> copies = hibernateRepository.getAllCopies();
        List<CopyDTO> copyDTOS = new ArrayList<>(copies.size());
        for (Copy copy : copies) {
            copyDTOS.add(ProjectModelMapper.convertCopyToDTO(copy));
        }
        return copyDTOS;
    }

    @Override
    @Transactional
    public CopyDTO insertCopy(CopyDTO copyDTO) throws ValidationException {
        String copyStatus = copyDTO.getCopyStatus().toUpperCase();
        if (copyStatus.compareTo(String.valueOf(CopyStatus.PENDING)) != 0) {
            throw new ValidationException("Status must be pending!");
        }
        BookDTO foundBook = bookService.findById(copyDTO.getBookId());
        if (foundBook == null) {
            throw new ValidationException("The book with given id does not exist!");
        }
        Copy copyToInsert = ProjectModelMapper.convertDTOtoCopy(copyDTO);
        copyToInsert.setBook(ProjectModelMapper.convertDTOtoBook(foundBook));
        return ProjectModelMapper.convertCopyToDTO(hibernateRepository.insertCopy(copyToInsert));
    }

    @Override
    @Transactional
    public boolean deleteCopy(int id) {
        Copy foundCopy = hibernateRepository.findCopyById(id);
        if (foundCopy == null) {
            return false;
        }
        hibernateRepository.deleteCopy(foundCopy);
        return true;
    }

    @Override
    @Transactional
    public CopyDTO updateCopy(CopyDTO copyDTO) {
        String copyStatus = copyDTO.getCopyStatus().toUpperCase();
        if (copyStatus.compareTo(String.valueOf(CopyStatus.AVAILABLE)) != 0 &&
                copyStatus.compareTo(String.valueOf(CopyStatus.PENDING)) != 0 &&
                copyStatus.compareTo(String.valueOf(CopyStatus.RENTED)) != 0) {
            return null;
        }
        Copy foundCopy = hibernateRepository.findCopyById(copyDTO.getId());
        if (foundCopy == null) {
            return null;
        }
        if (bookService.findById(copyDTO.getBookId()) == null) {
            return null;
        }
        return ProjectModelMapper.convertCopyToDTO(hibernateRepository.updateCopy(ProjectModelMapper.convertDTOtoCopy(copyDTO)));
    }

    @Override
    @Transactional
    public CopyDTO findCopyById(int id) {
        return ProjectModelMapper.convertCopyToDTO(hibernateRepository.findCopyById(id));
    }
}
