package com.arobs.project.employee;

import com.arobs.project.dtos.EmployeeDTO;
import com.arobs.project.exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/library-app")
public class EmployeeController {
    private EmployeeService service;
    private final Logger log = LoggerFactory.getLogger("FILE");

    @Autowired
    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping(value = "/employees")
    public ResponseEntity<?> handleFindAllEmployees() {
        log.info("Employees found!");
        return new ResponseEntity<>(service.findAllEmployees(), HttpStatus.OK);
    }

    @PostMapping(value = "/employees")
    public ResponseEntity<?> handleInsertEmployee(@RequestBody EmployeeDTO employeeDTO) {
        log.info("Employee inserted!");
        return new ResponseEntity<>(service.insertEmployee(employeeDTO), HttpStatus.OK);
    }

    @DeleteMapping(value = "/employees/{id}")
    public ResponseEntity<String> handleDeleteEmployee(@PathVariable int id) {
        if (service.deleteEmployee(id)) {
            log.info("Employee successfully deleted!");
            return new ResponseEntity<>("Employee successfully deleted!", HttpStatus.OK);
        } else {
            log.error("Employee with given id does not exist!");
            return new ResponseEntity<>("Employee with given id does not exist!", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/employees")
    public ResponseEntity<?> handleUpdateEmployee(@RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO updatedEmployee = service.updateEmployee(employeeDTO);
        if (null == updatedEmployee) {
            log.error("The given employee does not exist!");
            return new ResponseEntity<>("The given employee does not exist!", HttpStatus.NOT_FOUND);
        }
        log.info("Employee updated!");
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    @GetMapping(value = "/employees/{id}")
    public ResponseEntity<?> handleFindEmployeeById(@PathVariable int id) {
        try {
            log.info("Employee found!");
            return new ResponseEntity<>(service.findEmployeeByID(id), HttpStatus.OK);
        } catch (ValidationException ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
