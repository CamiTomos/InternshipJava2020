package com.arobs.project.bookRent;


import com.arobs.project.dtos.BookRentDTO;
import com.arobs.project.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController("bookRentController")
@RequestMapping("/library-app")
public class BookRentController {
    private BookRentService bookRentService;

    @Autowired
    public BookRentController(BookRentService bookRentService) {
        this.bookRentService = bookRentService;
    }

    @PostMapping(value = "/bookRents")
    public ResponseEntity<?> handleInsertCopy(@RequestBody BookRentDTO bookRentDTO) {
        try {
            return new ResponseEntity<>(bookRentService.insertBookRent(bookRentDTO), HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (ParseException e) {
            return new ResponseEntity<>("Date does not respect the format!", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/bookRents/extend/{id}")
    public ResponseEntity<?> handleExtendDeadline(@PathVariable int id) {
        try {
            bookRentService.extendDeadlineBookRent(id);
            return new ResponseEntity<>("Return date extended successfully!", HttpStatus.OK);
        } catch (ValidationException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
