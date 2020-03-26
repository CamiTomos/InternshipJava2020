package com.arobs.project.rent.bookRent;


import com.arobs.project.dtos.BookRentDTO;
import com.arobs.project.exception.ValidationException;
import com.arobs.project.rent.RentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController("bookRentController")
@RequestMapping("/library-app")
public class BookRentController {
    private final RentService rentService;
    private final Logger log = LoggerFactory.getLogger("FILE");

    @Autowired
    public BookRentController(RentService rentService) {
        this.rentService = rentService;
    }


    @PostMapping(value = "/bookRents")
    public ResponseEntity<?> handleInsertBookRent(@Valid @RequestBody BookRentDTO bookRentDTO) {
        try {
            log.info("Book rent inserted!");
            String returnedMessage = rentService.insertBookRent(bookRentDTO.getEmployeeId(), bookRentDTO.getBookId()).getMessage();
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        } catch (ValidationException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Server exception!");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/bookRents/extend/{id}")
    public ResponseEntity<?> handleExtendDeadline(@NotNull @PathVariable int id) {
        try {
            log.info("Deadline extended!");
            rentService.extendDeadlineBookRent(id);
            return new ResponseEntity<>("Return date extended successfully!", HttpStatus.OK);
        } catch (ValidationException ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Server exception!");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/bookRents/return/{id}/{grade}")
    public ResponseEntity<?> handleReturnBook(@NotNull @PathVariable int id, @NotNull @PathVariable double grade) {
        if (grade < 1 || grade > 5) {
            log.error("Grade must be between 1 and 5!");
            return new ResponseEntity<>("Grade must be between 1 and 5!", HttpStatus.NOT_ACCEPTABLE);
        }
        try {
            log.info("Book returned successfully!");
            rentService.returnBook(id, grade);
            return new ResponseEntity<>("Book returned successfully!", HttpStatus.OK);
        } catch (ValidationException ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Server exception!");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
