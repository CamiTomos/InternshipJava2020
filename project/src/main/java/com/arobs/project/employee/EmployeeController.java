package com.arobs.project.employee;

import com.arobs.project.dtos.EmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/library-app")
public class EmployeeController {
    private EmployeeService service;

    @Autowired
    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping(value = "/employees")
    public ResponseEntity<?> handleFindAllEmployees() {
        return new ResponseEntity<>(service.findAllEmployees(), HttpStatus.OK);
    }

    @PostMapping(value = "/employees")
    public ResponseEntity<?> handleInsertEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return new ResponseEntity<>(service.insertEmployee(employeeDTO), HttpStatus.OK);
    }

    @DeleteMapping(value = "/employees/{id}")
    public ResponseEntity<String> handleDeleteEmployee(@PathVariable int id) {
        if (service.deleteEmployee(id)) {
            return new ResponseEntity<>("Employee successfully deleted!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Employee with given id does not exist!", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/employees")
    public ResponseEntity<?> handleUpdateEmployee(@RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO updatedEmployee = service.updateEmployee(employeeDTO);
        if (null == updatedEmployee) {
            return new ResponseEntity<>("The given employee does not exist!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    @GetMapping(value = "/employees/{id}")
    public ResponseEntity<?> handleFindEmployeeById(@PathVariable int id) {
        EmployeeDTO foundEmployee = service.findEmployeeByID(id);
        if (null == foundEmployee) {
            return new ResponseEntity<>("The given employee does not exist!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(foundEmployee, HttpStatus.OK);
    }
}
