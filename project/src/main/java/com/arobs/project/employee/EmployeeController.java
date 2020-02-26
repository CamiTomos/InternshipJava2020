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
    public EmployeeController(EmployeeServiceImpl service) {
        this.service = service;
    }

    @GetMapping(value = "/employees")
    public ResponseEntity<?> handleGetAllEmployees() {
        return new ResponseEntity<>(service.getAllEmployees(), HttpStatus.OK);
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
            return new ResponseEntity<>("Employee was not deleted!", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/employees")
    public ResponseEntity<?> handleUpdateEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return new ResponseEntity<>(service.updateEmployee(employeeDTO), HttpStatus.OK);
    }

    @GetMapping(value = "/employees/{id}")
    public ResponseEntity<EmployeeDTO> handleFindEmployeeById(@PathVariable int id) {
        return new ResponseEntity<>(service.findEmployeeByID(id), HttpStatus.OK);
    }
}
