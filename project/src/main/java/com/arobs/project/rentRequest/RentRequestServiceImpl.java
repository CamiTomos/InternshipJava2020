package com.arobs.project.rentRequest;

import com.arobs.project.book.Book;
import com.arobs.project.book.BookService;
import com.arobs.project.dtos.RentRequestDTO;
import com.arobs.project.employee.Employee;
import com.arobs.project.employee.EmployeeService;
import com.arobs.project.enums.RentRequestStatus;
import com.arobs.project.exception.ValidationException;
import com.arobs.project.mappers.ProjectModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service("rentRequestServiceImpl")
@EnableTransactionManagement
public class RentRequestServiceImpl implements RentRequestService {
    private RentRequestHibernateRepository rentRequestRepository;
    private BookService bookService;
    private EmployeeService employeeService;

    @Autowired
    public RentRequestServiceImpl(RentRequestHibernateRepository rentRequestRepository, BookService bookService, EmployeeService employeeService) {
        this.rentRequestRepository = rentRequestRepository;
        this.bookService = bookService;
        this.employeeService = employeeService;
    }


    @Override
    @Transactional
    public void insertRentRequest(RentRequestDTO rentRequestDTO) throws ValidationException {
        Book foundBook = ProjectModelMapper.convertDTOtoBook(bookService.findById(rentRequestDTO.getBookId()));
        Employee foundEmployee = ProjectModelMapper.convertDTOtoEmployee(employeeService.findEmployeeByID(rentRequestDTO.getEmployeeId()));
        Timestamp requestDate = new Timestamp(System.currentTimeMillis());
        String rentRequestStatus = RentRequestStatus.WAITING_AVAILABLE.toString().toLowerCase();
        RentRequest rentRequestToInsert = new RentRequest(0, requestDate, rentRequestStatus, foundEmployee, foundBook);
        rentRequestRepository.insertRentRequest(rentRequestToInsert);
    }

    @Override
    @Transactional
    public void acceptRentRequest(RentRequest rentRequest) throws ValidationException {
        rentRequestRepository.updateRentRequest(rentRequest);
    }

    @Override
    public RentRequest getRentRequestById(int id) throws ValidationException {
        RentRequest rentRequest=rentRequestRepository.getRentRequestById(id);
        if(null==rentRequest){
            throw new ValidationException("There is no rent request with given id!");
        }
        return rentRequest;
    }

    @Override
    @Transactional
    public void declineRentRequest(RentRequest rentRequest) throws ValidationException {
        rentRequestRepository.updateRentRequest(rentRequest);
    }

    @Override
    @Transactional
    public List<RentRequest> findWaitingAvailableCopiesRequests(int bookId) {
        List<RentRequest> foundRentRequests = rentRequestRepository.findWaitingAvailableCopiesRequests(bookId);
        return foundRentRequests;
    }

    @Override
    public void sendEmail(RentRequest selectedRequest) {
        rentRequestRepository.updateRentRequest(selectedRequest);
    }


}
