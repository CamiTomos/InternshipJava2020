package com.arobs.project.copy;

import com.arobs.project.book.Book;
import com.arobs.project.book.BookService;
import com.arobs.project.enums.CopyStatus;
import com.arobs.project.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

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
    public List<Copy> findAllCopies() {
        return hibernateRepository.findAllCopies();
    }

    @Override
    @Transactional
    public Copy insertCopy(Copy copy) throws ValidationException {
        String copyStatus = copy.getCopyStatus().toUpperCase();
        if (copyStatus.compareTo(String.valueOf(CopyStatus.AVAILABLE)) != 0) {
            throw new ValidationException("Status must be available!");
        }
        Book foundBook = bookService.findBookById(copy.getBook().getId());
        if (null == foundBook) {
            throw new ValidationException("The book with given id does not exist!");
        }
        return hibernateRepository.insertCopy(copy);
    }

    @Override
    @Transactional
    public boolean deleteCopy(int id) {
        Copy foundCopy = hibernateRepository.findCopyById(id);
        if (null == foundCopy) {
            return false;
        }
        hibernateRepository.deleteCopy(foundCopy);
        return true;
    }

    @Override
    @Transactional
    public Copy updateCopy(Copy copy) throws ValidationException {
        String copyStatus = copy.getCopyStatus().toUpperCase();
        if (copyStatus.compareTo(String.valueOf(CopyStatus.AVAILABLE)) != 0 &&
                copyStatus.compareTo(String.valueOf(CopyStatus.PENDING)) != 0 &&
                copyStatus.compareTo(String.valueOf(CopyStatus.RENTED)) != 0) {
            throw new ValidationException("Status must be available, pending or rented!");
        }
        if (hibernateRepository.findCopyById(copy.getId()) == null) {
            throw new ValidationException("Copy with given id does not exist!");
        }
        if (bookService.findBookById(copy.getBook().getId()) == null) {
            throw new ValidationException("Book with given id does not exist!");
        }
        return hibernateRepository.updateCopy(copy);
    }

    @Override
    @Transactional
    public Copy findCopyById(int id) throws ValidationException {
        Copy foundCopy = hibernateRepository.findCopyById(id);
        if (null == foundCopy) {
            throw new ValidationException("Copy with given id does not exist!");
        }
        return foundCopy;
    }

    @Override
    @Transactional
    public List<Copy> findAvailableCopiesForBook(int bookId) {
        return hibernateRepository.findAvailableCopiesForBook(bookId);
//                .stream()
//                .map(ProjectModelMapper::convertCopyToDTO)
//                .collect(Collectors.toList());
    }

    @Override
    public List<Copy> findPendingCopiesForBook(int bookId) {
        return hibernateRepository.findPendingCopiesForBook(bookId);
//                .stream()
//                .map(ProjectModelMapper::convertCopyToDTO)
//                .collect(Collectors.toList());
    }

    @Override
    public List<Copy> findRentedCopiesForBook(int bookId) {
        return hibernateRepository.findRentedCopiesForBook(bookId);
//                .stream()
//                .map(ProjectModelMapper::convertCopyToDTO)
//                .collect(Collectors.toList());
    }
}
