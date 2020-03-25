package com.arobs.project.bookRequest;

import com.arobs.project.employee.Employee;
import com.arobs.project.employee.EmployeeService;
import com.arobs.project.enums.BookRequestStatus;
import com.arobs.project.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("bookRequestServiceImpl")
@EnableTransactionManagement
public class BookRequestServiceImpl implements BookRequestService {
    private BookRequestHibernateRepository bookRequestRepository;
    private EmployeeService employeeService;

    @Autowired
    public BookRequestServiceImpl(BookRequestHibernateRepository bookRequestRepository, EmployeeService employeeService) {
        this.bookRequestRepository = bookRequestRepository;
        this.employeeService = employeeService;
    }

    @Override
    @Transactional
    public List<BookRequest> findAllBookRequests() {
        return bookRequestRepository.findAllBookRequests();
    }

    @Override
    @Transactional
    public BookRequest insertBookRequest(BookRequest bookRequest) throws ValidationException {
        Employee employee = employeeService.findEmployeeByID(bookRequest.getEmployee().getId());
        if (null == employee) {
            throw new ValidationException("The given employee does not exist!");
        }
        if (bookRequest.getBookrequestStatus().toUpperCase().compareTo(String.valueOf(BookRequestStatus.PENDING)) != 0) {
            throw new ValidationException("Status must be pending!");
        }
        return bookRequestRepository.insertBookRequest(bookRequest);
    }

    @Override
    @Transactional
    public boolean deleteBookRequest(int id) {
        BookRequest foundBookRequest = bookRequestRepository.findBookRequestById(id);
        if (null == foundBookRequest) {
            return false;
        }
        return bookRequestRepository.deleteBookRequest(foundBookRequest);
    }

    @Override
    @Transactional
    public BookRequest updateBookRequest(BookRequest bookRequest) throws ValidationException {
        BookRequest foundBookRequest = bookRequestRepository.findBookRequestById(bookRequest.getId());
        if (null == foundBookRequest) {
            throw new ValidationException("BookRequest with given id does not exist!");
        }
        String givenStatus = bookRequest.getBookrequestStatus().toUpperCase();
        if (givenStatus.compareTo(String.valueOf(BookRequestStatus.PENDING)) != 0 &&
                givenStatus.compareTo(String.valueOf(BookRequestStatus.ACCEPTED)) != 0 &&
                givenStatus.compareTo(String.valueOf(BookRequestStatus.REJECTED)) != 0) {
            throw new ValidationException("Status must be pending or accepted or rejected!");
        }
        return bookRequestRepository.updateBookRequest(bookRequest);
    }

    @Override
    @Transactional
    public BookRequest findBookRequestById(int id) throws ValidationException {
        BookRequest foundBookRequest = bookRequestRepository.findBookRequestById(id);
        if (null == foundBookRequest) {
            throw new ValidationException("Book request with given id does not exist!");
        }
        return foundBookRequest;
    }
}
