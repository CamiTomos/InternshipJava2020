package com.arobs.project.rent;

import com.arobs.project.book.Book;
import com.arobs.project.book.BookService;
import com.arobs.project.copy.Copy;
import com.arobs.project.copy.CopyService;
import com.arobs.project.employee.Employee;
import com.arobs.project.employee.EmployeeService;
import com.arobs.project.enums.BookRentStatus;
import com.arobs.project.enums.CopyStatus;
import com.arobs.project.enums.RentRequestStatus;
import com.arobs.project.exception.ValidationException;
import com.arobs.project.operationObjects.OperationObject;
import com.arobs.project.rent.bookRent.BookRent;
import com.arobs.project.rent.bookRent.BookRentHibernateRepository;
import com.arobs.project.rent.rentRequest.RentRequest;
import com.arobs.project.rent.rentRequest.RentRequestHibernateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service("rentService")
@EnableTransactionManagement
public class RentServiceImpl implements RentService {
    private final RentRequestHibernateRepository rentRequestRepository;
    private final BookRentHibernateRepository bookRentRepository;
    private final BookService bookService;
    private final CopyService copyService;
    private final EmployeeService employeeService;
    private final Logger log = LoggerFactory.getLogger("FILE");

    @Autowired
    public RentServiceImpl(RentRequestHibernateRepository rentRequestRepository,
                           BookRentHibernateRepository bookRentRepository, BookService bookService,
                           CopyService copyService, EmployeeService employeeService) {
        this.rentRequestRepository = rentRequestRepository;
        this.bookRentRepository = bookRentRepository;
        this.bookService = bookService;
        this.copyService = copyService;
        this.employeeService = employeeService;
    }

    @Override
    @Transactional
    public OperationObject insertBookRent(int employeeId, int bookId) throws ValidationException {
        Employee foundEmployee = employeeService.findEmployeeByID(employeeId);
        if (foundEmployee.isBanned()) {
            throw new ValidationException("The employee is banned!");
        }
        Book foundBook = bookService.findBookById(bookId);
        List<Copy> foundAvailableCopies = copyService.findAvailableCopiesForBook(foundBook.getId());
        if (foundAvailableCopies.isEmpty()) {
            log.info("No available copy for this book found!");
            insertRentRequest(employeeId, bookId);
            return new OperationObject("There are no available books! Rent request created!");
        }
        Copy foundCopy = foundAvailableCopies.get(0);
        foundCopy.setCopyStatus(CopyStatus.RENTED.toString());
        Timestamp rentalDate = new Timestamp(System.currentTimeMillis());
        Timestamp returnDate = this.createTimestampReturnDate(rentalDate);
        BookRent bookRentToInsert = new BookRent(rentalDate, returnDate, BookRentStatus.ON_GOING.toString(), 0.0, foundEmployee, foundCopy, foundBook);
        bookRentRepository.insertBookRent(bookRentToInsert);
        return new OperationObject("Book rent inserted successfully!");
    }

    private Timestamp createTimestampReturnDate(Timestamp timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(timestamp);
        calendar.add(Calendar.DAY_OF_WEEK, 30);
        return new Timestamp(calendar.getTime().getTime());
    }

    @Override
    @Transactional
    public void returnBook(int id, double grade) throws ValidationException {
        BookRent foundBookRent = findBookRentById(id);
        if (null == foundBookRent) {
            throw new ValidationException("Book rent not found!");
        }
        foundBookRent.setBookrentReturnDate(new Timestamp(System.currentTimeMillis()));
        foundBookRent.setBookrentNote(grade);
        foundBookRent.setBookrentStatus(BookRentStatus.RETURNED.toString());
        returnBook(foundBookRent);
        List<RentRequest> requestsFoundForThisBook = findWaitingAvailableCopiesRequests(foundBookRent.getBook().getId());
        Copy foundCopy = foundBookRent.getCopy();
        if (!requestsFoundForThisBook.isEmpty()) {
            RentRequest selectedRequest = requestsFoundForThisBook.get(0);
            foundCopy.setCopyStatus(CopyStatus.PENDING.toString());
            selectedRequest.setRentrequestStatus(RentRequestStatus.WAITING_CONFIRMATION.toString());
            selectedRequest.setEmailSentDate(new Timestamp(System.currentTimeMillis()));
            sendEmail(selectedRequest);
        } else {
            foundCopy.setCopyStatus(CopyStatus.AVAILABLE.toString());
        }
    }

    private void returnBook(BookRent bookRent) {
        bookRentRepository.updateBookRent(bookRent);
    }

    private List<RentRequest> findWaitingAvailableCopiesRequests(int bookId) {
        return rentRequestRepository.findWaitingAvailableCopiesRequests(bookId);
    }

    private BookRent findBookRentById(int id) throws ValidationException {
        BookRent foundBookRent = bookRentRepository.getBookRentById(id);
        if (null == foundBookRent) {
            throw new ValidationException("Book rent not found!");
        }
        return foundBookRent;
    }

    @Override
    @Transactional
    public void acceptRentRequest(int id) throws ValidationException {
        RentRequest foundRentRequest = getRentRequestById(id);
        if (null == foundRentRequest) {
            throw new ValidationException("Rent request with given id does not exist!");
        }
        Copy copy = copyService.findPendingCopiesForBook(foundRentRequest.getBook().getId()).get(0);
        copy.setCopyStatus(CopyStatus.AVAILABLE.toString());
        insertBookRent(foundRentRequest.getEmployee().getId(), foundRentRequest.getBook().getId());
        foundRentRequest.setRentrequestStatus(RentRequestStatus.GRANTED.toString());
        acceptRentRequest(foundRentRequest);
    }

    private void acceptRentRequest(RentRequest rentRequest) {
        rentRequestRepository.updateRentRequest(rentRequest);
    }

    private RentRequest getRentRequestById(int id) throws ValidationException {
        RentRequest rentRequest = rentRequestRepository.getRentRequestById(id);
        if (null == rentRequest) {
            throw new ValidationException("There is no rent request with given id!");
        }
        return rentRequest;
    }

    @Override
    @Transactional
    public void declineRentRequest(int id) throws ValidationException {
        RentRequest foundRentRequest = getRentRequestById(id);
        if (null == foundRentRequest) {
            throw new ValidationException("Rent request with given id does not exist!");
        }
        List<RentRequest> requestsFoundForThisBook = rentRequestRepository.findWaitingAvailableCopiesRequests(foundRentRequest.getBook().getId());
        Copy foundCopy = copyService.findPendingCopiesForBook(foundRentRequest.getBook().getId()).get(0);
        if (!requestsFoundForThisBook.isEmpty()) {
            RentRequest selectedRequest = requestsFoundForThisBook.get(0);
            foundCopy.setCopyStatus(CopyStatus.PENDING.toString());
            selectedRequest.setRentrequestStatus(RentRequestStatus.WAITING_CONFIRMATION.toString());
            selectedRequest.setEmailSentDate(new Timestamp(System.currentTimeMillis()));
            sendEmail(selectedRequest);
        } else {
            foundCopy.setCopyStatus(CopyStatus.AVAILABLE.toString());
        }
        foundRentRequest.setRentrequestStatus(RentRequestStatus.DECLINED.toString());
        declineRentRequest(foundRentRequest);
    }

    private void sendEmail(RentRequest selectedRequest) {
        rentRequestRepository.updateRentRequest(selectedRequest);
    }

    private void declineRentRequest(RentRequest rentRequest) {
        rentRequestRepository.updateRentRequest(rentRequest);
    }

    @Override
    @Transactional
    public void insertRentRequest(int employeeId, int bookId) throws ValidationException {
        Book foundBook = bookService.findBookById(bookId);
        Employee foundEmployee = employeeService.findEmployeeByID(employeeId);
        Timestamp requestDate = new Timestamp(System.currentTimeMillis());
        String rentRequestStatus = RentRequestStatus.WAITING_AVAILABLE.toString();
        RentRequest rentRequestToInsert = new RentRequest(requestDate, rentRequestStatus, foundEmployee, foundBook);
        rentRequestRepository.insertRentRequest(rentRequestToInsert);
    }

    @Override
    @Transactional
    public void extendDeadlineBookRent(int id) throws ValidationException {
        BookRent foundBookRent = bookRentRepository.getBookRentById(id);
        if (null == foundBookRent) {
            throw new ValidationException("Book rent not found!");
        }
        if (foundBookRent.getBookrentStatus().compareTo(BookRentStatus.ON_GOING.toString()) != 0) {
            throw new ValidationException("You can not extend rental date! Book does not have on_going status!");
        }
        Timestamp rentalDate = foundBookRent.getBookrentRentalDate();
        Timestamp returnDate = foundBookRent.getBookrentReturnDate();
        Calendar rentalCalendar = Calendar.getInstance();
        rentalCalendar.setTime(rentalDate);
        Calendar returnCalendar = Calendar.getInstance();
        returnCalendar.setTime(returnDate);
        returnCalendar.add(Calendar.DAY_OF_WEEK, 15);
        int monthsDifference = returnCalendar.get(Calendar.MONTH) - rentalCalendar.get(Calendar.MONTH);
        if (monthsDifference >= 3) {
            throw new ValidationException("You can not extend rental date! Rental becomes more than 3 months total time!");
        }
        foundBookRent.setBookrentReturnDate(new Timestamp(returnCalendar.getTime().getTime()));
        bookRentRepository.updateBookRent(foundBookRent);
    }

    @Override
    @Transactional
    public void markRentalsLate() {
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        List<BookRent> lateRentals = bookRentRepository.markRentalsLate(currentTime);
        lateRentals.forEach(bookRent -> {
            bookRent.setBookrentStatus("LATE");
            Employee foundEmployee = bookRent.getEmployee();
            banEmployee(bookRent);
            foundEmployee.setBanned(true);
        });
        List<Employee> bannedEmplyees = employeeService.findAllEmployees()
                .stream()
                .filter(employee -> employee.isBanned())
                .collect(Collectors.toList());
        permitRentForEmployees(bannedEmplyees);
    }

    private void banEmployee(BookRent foundBookRent) {
        Employee foundEmployee = foundBookRent.getEmployee();
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        Calendar currentCalendar = Calendar.getInstance();
        currentCalendar.setTime(currentTime);
        Calendar returnCalendar = Calendar.getInstance();
        returnCalendar.setTime(foundBookRent.getBookrentReturnDate());
        int daysDifference = currentCalendar.get(Calendar.DAY_OF_WEEK) - returnCalendar.get(Calendar.DAY_OF_WEEK);
        if (daysDifference > 10) {
            daysDifference *= 2;
            returnCalendar.add(Calendar.DAY_OF_WEEK, daysDifference);
        } else {
            returnCalendar.add(Calendar.DAY_OF_WEEK, 10);
        }
        foundEmployee.setLastDayOfBan(new Timestamp(returnCalendar.getTime().getTime()));
    }

    private void permitRentForEmployees(List<Employee> bannedEmployees) {
        bannedEmployees.forEach(employee -> {
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            if (employee.getLastDayOfBan() != null && currentTime.after(employee.getLastDayOfBan())) {
                employee.setBanned(false);
            }
        });
    }

    @Override
    @Transactional
    public void checkEmailConfirmation() {
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        List<RentRequest> waitingConfirmationRequests = rentRequestRepository.findWaitingConfirmationRequests();
        waitingConfirmationRequests.forEach(
                rentRequest -> {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(rentRequest.getEmailSentDate());
                    calendar.add(Calendar.HOUR_OF_DAY, 24);
                    if (currentTime.after(calendar.getTime())) {
                        rentRequest.setRentrequestStatus(RentRequestStatus.DECLINED.toString());
                        rentRequestRepository.updateRentRequest(rentRequest);
                        Book book = rentRequest.getBook();
                        List<Copy> pendingCopies = copyService.findPendingCopiesForBook(book.getId());
                        Copy copyToBeUpdated = pendingCopies.get(0);
                        notifyNextEmployee(book.getId(), copyToBeUpdated);
                    }
                }
        );
    }

    private void notifyNextEmployee(int bookId, Copy copy) {
        List<RentRequest> requestsFoundForThisBook = findWaitingAvailableCopiesRequests(bookId);
        if (!requestsFoundForThisBook.isEmpty()) {
            RentRequest selectedRequest = requestsFoundForThisBook.get(0);
            copy.setCopyStatus(CopyStatus.PENDING.toString());
            selectedRequest.setRentrequestStatus(RentRequestStatus.WAITING_CONFIRMATION.toString());
            selectedRequest.setEmailSentDate(new Timestamp(System.currentTimeMillis()));
            sendEmail(selectedRequest);
        } else {
            copy.setCopyStatus(CopyStatus.AVAILABLE.toString());
        }
    }
}
