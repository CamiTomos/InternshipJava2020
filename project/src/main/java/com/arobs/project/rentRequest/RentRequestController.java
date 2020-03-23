package com.arobs.project.rentRequest;


import com.arobs.project.dtos.RentRequestDTO;
import com.arobs.project.exception.ValidationException;
import com.arobs.project.managers.RentManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController("rentRequestController")
@RequestMapping("/library-app")
public class RentRequestController {
    private RentRequestService rentRequestService;
    private RentManager rentManager;

    @Autowired
    public RentRequestController(RentRequestService rentRequestService, RentManager rentManager) {
        this.rentRequestService = rentRequestService;
        this.rentManager = rentManager;
    }

    @PostMapping(value = "/rentRequests")
    public ResponseEntity<?> handleInsertRentRequest(@RequestBody RentRequestDTO rentRequestDTO) {
        try {
            rentRequestService.insertRentRequest(rentRequestDTO);
            return new ResponseEntity<>("Rent request successfully inserted!", HttpStatus.OK);
        } catch (ValidationException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/rentRequests/accept/{id}")
    public ResponseEntity<?> handleAcceptRequest(@PathVariable int id) {
        try {
            rentManager.acceptRentRequest(id);
            return new ResponseEntity<>("Rent request successfully accepted!", HttpStatus.OK);
        } catch (ValidationException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (ParseException e) {
            return new ResponseEntity<>("Date can not be parsed!", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/rentRequests/decline/{id}")
    public ResponseEntity<?> handleDeclineRequest(@PathVariable int id) {
        try {
            rentManager.declineRentRequest(id);
            return new ResponseEntity<>("Rent request successfully declined!", HttpStatus.OK);
        } catch (ValidationException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (ParseException e) {
            return new ResponseEntity<>("Date can not be parsed!", HttpStatus.BAD_REQUEST);
        }
    }
}
