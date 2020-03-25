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

import java.util.List;
import java.util.stream.Collectors;

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
    public List<CopyDTO> findAllCopies() {
        return hibernateRepository.findAllCopies()
                .stream()
                .map(ProjectModelMapper::convertCopyToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CopyDTO insertCopy(CopyDTO copyDTO) throws ValidationException {
        String copyStatus = copyDTO.getCopyStatus().toUpperCase();
        if (copyStatus.compareTo(String.valueOf(CopyStatus.AVAILABLE)) != 0) {
            throw new ValidationException("Status must be available!");
        }
        BookDTO foundBook = bookService.findBookById(copyDTO.getBookId());
        if (foundBook == null) {
            throw new ValidationException("The book with given id does not exist!");
        }
        Copy copyToInsert = ProjectModelMapper.convertDTOtoCopy(copyDTO);
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
    public CopyDTO updateCopy(CopyDTO copyDTO) throws ValidationException {
        String copyStatus = copyDTO.getCopyStatus().toUpperCase();
        if (copyStatus.compareTo(String.valueOf(CopyStatus.AVAILABLE)) != 0 &&
                copyStatus.compareTo(String.valueOf(CopyStatus.PENDING)) != 0 &&
                copyStatus.compareTo(String.valueOf(CopyStatus.RENTED)) != 0) {
            throw new ValidationException("Status must be available, pendind or rented!");
        }
        if (hibernateRepository.findCopyById(copyDTO.getId()) == null) {
            throw new ValidationException("Copy with given id does not exist!");
        }
        if (bookService.findBookById(copyDTO.getBookId()) == null) {
            throw new ValidationException("Book with given id does not exist!");
        }
        return ProjectModelMapper.convertCopyToDTO(hibernateRepository.updateCopy(ProjectModelMapper.convertDTOtoCopy(copyDTO)));
    }

    @Override
    @Transactional
    public CopyDTO findCopyById(int id) throws ValidationException {
        Copy foundCopy = hibernateRepository.findCopyById(id);
        if (foundCopy == null) {
            throw new ValidationException("Copy with given id does not exist!");
        }
        return ProjectModelMapper.convertCopyToDTO(foundCopy);
    }

    @Override
    @Transactional
    public List<CopyDTO> findAvailableCopiesForBook(int bookId) {
        return hibernateRepository.findAvailableCopiesForBook(bookId)
                .stream()
                .map(ProjectModelMapper::convertCopyToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CopyDTO> findPendingCopiesForBook(int bookId) {
        return hibernateRepository.findPendingCopiesForBook(bookId)
                .stream()
                .map(ProjectModelMapper::convertCopyToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CopyDTO> findRentedCopiesForBook(int bookId) {
        return hibernateRepository.findRentedCopiesForBook(bookId)
                .stream()
                .map(ProjectModelMapper::convertCopyToDTO)
                .collect(Collectors.toList());
    }
}
