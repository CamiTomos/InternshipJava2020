package com.arobs.project.rent.rentRequest;


import com.arobs.project.dtos.RentRequestDTO;
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
import javax.validation.constraints.NotBlank;

@Validated
@RestController("rentRequestController")
@RequestMapping("/library-app")
public class RentRequestController {
    private final RentService rentService;
    private final Logger log = LoggerFactory.getLogger("FILE");

    @Autowired
    public RentRequestController(RentService rentService) {
        this.rentService = rentService;
    }

    @PostMapping(value = "/rentRequests")
    public ResponseEntity<?> handleInsertRentRequest(@Valid @RequestBody RentRequestDTO rentRequestDTO) {
        try {
            log.info("Rent request successfully inserted!");
            rentService.insertRentRequest(rentRequestDTO.getEmployeeId(), rentRequestDTO.getBookId());
            return new ResponseEntity<>("Rent request successfully inserted!", HttpStatus.OK);
        } catch (ValidationException ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Server exception!");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/rentRequests/accept/{id}")
    public ResponseEntity<?> handleAcceptRequest(@NotBlank @PathVariable int id) {
        try {
            log.info("Rent request successfully accepted!");
            rentService.acceptRentRequest(id);
            return new ResponseEntity<>("Rent request successfully accepted!", HttpStatus.OK);
        } catch (ValidationException ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Server exception!");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/rentRequests/decline/{id}")
    public ResponseEntity<?> handleDeclineRequest(@NotBlank @PathVariable int id) {
        try {
            log.info("Rent request successfully declined!");
            rentService.declineRentRequest(id);
            return new ResponseEntity<>("Rent request successfully declined!", HttpStatus.OK);
        } catch (ValidationException ex) {
            log.error("Rent request successfully declined!");
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Server exception!");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
