package com.arobs.project.managers;

import com.arobs.project.book.Book;
import com.arobs.project.book.BookService;
import com.arobs.project.bookRent.BookRent;
import com.arobs.project.bookRent.BookRentService;
import com.arobs.project.copy.Copy;
import com.arobs.project.copy.CopyService;
import com.arobs.project.dtos.BookRentDTO;
import com.arobs.project.dtos.CopyDTO;
import com.arobs.project.dtos.RentRequestDTO;
import com.arobs.project.employee.Employee;
import com.arobs.project.employee.EmployeeService;
import com.arobs.project.enums.BookRentStatus;
import com.arobs.project.enums.CopyStatus;
import com.arobs.project.enums.RentRequestStatus;
import com.arobs.project.exception.ValidationException;
import com.arobs.project.mappers.ProjectModelMapper;
import com.arobs.project.rentRequest.RentRequest;
import com.arobs.project.rentRequest.RentRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

@Service("rentManager")
@EnableTransactionManagement
public class RentManager {
    private BookRentService bookRentService;
    private RentRequestService rentRequestService;
    private BookService bookService;
    private CopyService copyService;
    private EmployeeService employeeService;

    @Autowired
    public RentManager(BookRentService bookRentService, RentRequestService rentRequestService, BookService bookService, CopyService copyService, EmployeeService employeeService) {
        this.bookRentService = bookRentService;
        this.rentRequestService = rentRequestService;
        this.bookService = bookService;
        this.copyService = copyService;
        this.employeeService = employeeService;
    }


    @Transactional
    public String insertBookRent(BookRentDTO bookRentDTO) throws ValidationException, ParseException {
        Book foundBook = ProjectModelMapper.convertDTOtoBook(bookService.findById(bookRentDTO.getBookId()));
        Employee foundEmployee = ProjectModelMapper.convertDTOtoEmployee(employeeService.findEmployeeByID(bookRentDTO.getEmployeeId()));
        List<CopyDTO> foundAvailableCopies = copyService.findAvailableCopiesForBook(foundBook.getId());
        if (foundAvailableCopies.isEmpty()) {
            System.out.println("No available copy for this book found!");
            RentRequestDTO rentRequestToInsert = new RentRequestDTO(bookRentDTO.getEmployeeId(), bookRentDTO.getBookId());
            rentRequestService.insertRentRequest(rentRequestToInsert);
            return "There are no available books! Rent request created!";
        }
        Copy foundCopy = ProjectModelMapper.convertDTOtoCopy(foundAvailableCopies.get(0));
        foundCopy.setCopyStatus(CopyStatus.RENTED.toString().toLowerCase());
        copyService.updateCopy(ProjectModelMapper.convertCopyToDTO(foundCopy));
        Timestamp rentalDate = new Timestamp(System.currentTimeMillis());
        Timestamp returnDate = this.createTimestampReturnDate(rentalDate);
        BookRent bookRentToInsert = new BookRent(0, rentalDate, returnDate, BookRentStatus.ON_GOING.toString().toLowerCase(), 0.0, foundEmployee, foundCopy, foundBook);
        bookRentService.insertBookRent(bookRentToInsert);
        return "Book rent inserted successfully!";
    }

    private Timestamp createTimestampReturnDate(Timestamp timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(timestamp);
        calendar.add(Calendar.DAY_OF_WEEK, 30);
        return new Timestamp(calendar.getTime().getTime());
    }

    @Transactional
    public void returnBook(int id, double grade) throws ValidationException {
        BookRent foundBookRent = bookRentService.findBookRentById(id);
        if (null == foundBookRent) {
            throw new ValidationException("Book rent not found!");
        }
        foundBookRent.setBookrentReturnDate(new Timestamp(System.currentTimeMillis()));
        foundBookRent.setBookrentNote(grade);
        foundBookRent.setBookrentStatus(BookRentStatus.RETURNED.toString().toLowerCase());
        bookRentService.returnBook(foundBookRent);//here, the book is returned
        List<RentRequest> requestsFoundForThisBook = rentRequestService.findWaitingAvailableCopiesRequests(foundBookRent.getBook().getId());
        Copy foundCopy = foundBookRent.getCopy();
        if (!requestsFoundForThisBook.isEmpty()) {
            RentRequest selectedRequest = requestsFoundForThisBook.get(0);
            foundCopy.setCopyStatus(CopyStatus.PENDING.toString().toLowerCase());
            copyService.updateCopy(ProjectModelMapper.convertCopyToDTO(foundCopy));
            selectedRequest.setRentrequestStatus(RentRequestStatus.WAITING_CONFIRMATION.toString().toLowerCase());
            selectedRequest.setEmailSentDate(new Timestamp(System.currentTimeMillis()));
            rentRequestService.sendEmail(selectedRequest);//here, the email is sent
        } else {
            foundCopy.setCopyStatus(CopyStatus.AVAILABLE.toString().toLowerCase());
            copyService.updateCopy(ProjectModelMapper.convertCopyToDTO(foundCopy));
        }
    }

    @Transactional
    public void acceptRentRequest(int id) throws ValidationException, ParseException {
        RentRequest foundRentRequest = rentRequestService.getRentRequestById(id);
        if (null == foundRentRequest) {
            throw new ValidationException("Rent request with given id does not exist!");
        }
        CopyDTO copyDTO=copyService.findPendingCopiesForBook(foundRentRequest.getBook().getId()).get(0);
        copyDTO.setCopyStatus(CopyStatus.AVAILABLE.toString().toLowerCase());
        copyService.updateCopy(copyDTO);
        insertBookRent(new BookRentDTO(0,foundRentRequest.getEmployee().getId(),foundRentRequest.getBook().getId()));
        foundRentRequest.setRentrequestStatus(RentRequestStatus.GRANTED.toString().toLowerCase());
        rentRequestService.acceptRentRequest(foundRentRequest);
    }

    @Transactional
    public void declineRentRequest(int id) throws ValidationException, ParseException {
        RentRequest foundRentRequest = rentRequestService.getRentRequestById(id);
        if (null == foundRentRequest) {
            throw new ValidationException("Rent request with given id does not exist!");
        }
        List<RentRequest> requestsFoundForThisBook = rentRequestService.findWaitingAvailableCopiesRequests(foundRentRequest.getBook().getId());
        CopyDTO foundCopy = copyService.findPendingCopiesForBook(foundRentRequest.getBook().getId()).get(0);
        if (!requestsFoundForThisBook.isEmpty()) {
            RentRequest selectedRequest = requestsFoundForThisBook.get(0);
            foundCopy.setCopyStatus(CopyStatus.PENDING.toString().toLowerCase());
            copyService.updateCopy(foundCopy);
            selectedRequest.setRentrequestStatus(RentRequestStatus.WAITING_CONFIRMATION.toString().toLowerCase());
            selectedRequest.setEmailSentDate(new Timestamp(System.currentTimeMillis()));
            rentRequestService.sendEmail(selectedRequest);//here, the email is sent
        } else {
            foundCopy.setCopyStatus(CopyStatus.AVAILABLE.toString().toLowerCase());
            copyService.updateCopy(foundCopy);
        }

        foundRentRequest.setRentrequestStatus(RentRequestStatus.DECLINED.toString().toLowerCase());
        rentRequestService.declineRentRequest(foundRentRequest);
    }

}
