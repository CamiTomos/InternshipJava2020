package com.arobs.project.rentRequest;


import com.arobs.project.dtos.RentRequestDTO;
import com.arobs.project.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("rentRequestController")
@RequestMapping("/library-app")
public class RentRequestController {
    private RentRequestService rentRequestService;

    @Autowired
    public RentRequestController(RentRequestService rentRequestService) {
        this.rentRequestService = rentRequestService;
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
            rentRequestService.acceptRentRequest(id);
            return new ResponseEntity<>("Rent request successfully accepted!", HttpStatus.OK);
        } catch (ValidationException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/rentRequests/decline/{id}")
    public ResponseEntity<?> handleDeclineRequest(@PathVariable int id) {
        try {
            rentRequestService.declineRentRequest(id);
            return new ResponseEntity<>("Rent request successfully declined!", HttpStatus.OK);
        } catch (ValidationException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
