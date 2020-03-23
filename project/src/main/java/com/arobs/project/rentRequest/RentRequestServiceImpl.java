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
    public void acceptRentRequest(int id) throws ValidationException {
        RentRequest foundRentRequest = rentRequestRepository.getRentRequestById(id);
        if (null == foundRentRequest) {
            throw new ValidationException("Rent request with given id does not exist!");
        }
        foundRentRequest.setRentrequestStatus(RentRequestStatus.GRANTED.toString().toLowerCase());
        rentRequestRepository.updateRentRequest(foundRentRequest);
    }

    @Override
    @Transactional
    public void declineRentRequest(int id) throws ValidationException {
        RentRequest foundRentRequest = rentRequestRepository.getRentRequestById(id);
        if (null == foundRentRequest) {
            throw new ValidationException("Rent request with given id does not exist!");
        }
        foundRentRequest.setRentrequestStatus(RentRequestStatus.DECLINED.toString().toLowerCase());
        rentRequestRepository.updateRentRequest(foundRentRequest);
    }

    @Override
    @Transactional
    public boolean findRequestByBook(int bookId) {
        List<RentRequest> foundRentRequests = rentRequestRepository.findRentRequestForBook(bookId);
        return foundRentRequests.size() != 0;
    }


}
