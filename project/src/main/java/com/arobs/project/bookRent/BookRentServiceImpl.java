package com.arobs.project.bookRent;

import com.arobs.project.enums.BookRentStatus;
import com.arobs.project.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Calendar;

@Service("bookRentServiceImpl")
@EnableTransactionManagement
public class BookRentServiceImpl implements BookRentService {
    private BookRentHibernateRepository bookRentRepository;

    @Autowired
    public BookRentServiceImpl(BookRentHibernateRepository bookRentRepository) {
        this.bookRentRepository = bookRentRepository;
    }

    @Override
    @Transactional
    public BookRent insertBookRent(BookRent bookRent) {
        return bookRentRepository.insertBookRent(bookRent);
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
    public void returnBook(BookRent bookRent) {
        bookRentRepository.updateBookRent(bookRent);
    }

    @Override
    public BookRent findBookRentById(int id) throws ValidationException {
        BookRent foundBookRent = bookRentRepository.getBookRentById(id);
        if (null == foundBookRent) {
            throw new ValidationException("Book rent not found!");
        }
        return foundBookRent;
    }
}
