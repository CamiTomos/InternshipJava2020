package com.arobs.project.bookRent;

import com.arobs.project.book.Book;
import com.arobs.project.book.BookService;
import com.arobs.project.copy.Copy;
import com.arobs.project.copy.CopyService;
import com.arobs.project.dtos.BookRentDTO;
import com.arobs.project.dtos.CopyDTO;
import com.arobs.project.employee.Employee;
import com.arobs.project.employee.EmployeeService;
import com.arobs.project.enums.BookRentStatus;
import com.arobs.project.exception.ValidationException;
import com.arobs.project.mappers.ProjectModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

@Service("bookRentServiceImpl")
@EnableTransactionManagement
public class BookRentServiceImpl implements BookRentService {
    //    private static SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private BookRentHibernateRepository bookRentRepository;
    private BookService bookService;
    private CopyService copyService;
    private EmployeeService employeeService;

    @Autowired
    public BookRentServiceImpl(BookRentHibernateRepository bookRentRepository, BookService bookService, CopyService copyService, EmployeeService employeeService) {
        this.bookRentRepository = bookRentRepository;
        this.bookService = bookService;
        this.copyService = copyService;
        this.employeeService = employeeService;
    }

    @Override
    @Transactional
    public BookRentDTO insertBookRent(BookRentDTO bookRentDTO) throws ValidationException {
        Book foundBook = ProjectModelMapper.convertDTOtoBook(bookService.findById(bookRentDTO.getBookId()));
        Employee foundEmployee = ProjectModelMapper.convertDTOtoEmployee(employeeService.findEmployeeByID(bookRentDTO.getEmployeeId()));
        List<CopyDTO> foundAvailableCopies = copyService.findAvailableCopiesForBook(foundBook.getId());
        if (foundAvailableCopies.isEmpty()) {
            throw new ValidationException("No available copy for this book found!");
        }
        Copy foundCopy = ProjectModelMapper.convertDTOtoCopy(foundAvailableCopies.get(0));
        Timestamp rentalDate = new Timestamp(System.currentTimeMillis());
        Timestamp returnDate = this.createTimestampReturnDate(rentalDate);
        BookRent bookRentToInsert = new BookRent(0, rentalDate, returnDate, BookRentStatus.ON_GOING.toString().toLowerCase(), 0.0, foundEmployee, foundCopy, foundBook);
        return ProjectModelMapper.convertBookRentToDTO(bookRentRepository.insertBookRent(bookRentToInsert));
    }

    private Timestamp createTimestampReturnDate(Timestamp timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(timestamp);
        calendar.add(Calendar.DAY_OF_WEEK, 30);
        return new Timestamp(calendar.getTime().getTime());
    }

    @Override
    @Transactional
    public void extendDeadlineBookRent(int id) throws ValidationException {
        BookRent foundBookRent = bookRentRepository.getBookRentById(id);
        if (null == foundBookRent) {
            throw new ValidationException("Book rent not found!");
        }
        if (foundBookRent.getBookrentStatus().compareTo(BookRentStatus.ON_GOING.toString().toLowerCase()) != 0) {
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
    public void returnBook(int id, double grade) throws ValidationException {
        BookRent foundBookRent = bookRentRepository.getBookRentById(id);
        if (null == foundBookRent) {
            throw new ValidationException("Book rent not found!");
        }
        foundBookRent.setBookrentReturnDate(new Timestamp(System.currentTimeMillis()));
        foundBookRent.setBookrentNote(grade);
        foundBookRent.setBookrentStatus(BookRentStatus.RETURNED.toString().toLowerCase());
        bookRentRepository.updateBookRent(foundBookRent);
    }
}
