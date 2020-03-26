package com.arobs.project.employee;

import com.arobs.project.dtos.EmployeeDTO;
import com.arobs.project.exception.ValidationException;
import com.arobs.project.mappers.ProjectModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Validated
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
        List<EmployeeDTO> foundEmployees = service.findAllEmployees()
                .stream()
                .map(ProjectModelMapper::convertEmployeeToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(foundEmployees, HttpStatus.OK);
    }

    @PostMapping(value = "/employees")
    public ResponseEntity<?> handleInsertEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        log.info("Employee inserted!");
        Employee employeeToInsert = ProjectModelMapper.convertDTOtoEmployee(employeeDTO);
        return new ResponseEntity<>(ProjectModelMapper.convertEmployeeToDTO(service.insertEmployee(employeeToInsert)), HttpStatus.OK);
    }

    @DeleteMapping(value = "/employees/{id}")
    public ResponseEntity<String> handleDeleteEmployee(@NotNull @PathVariable int id) {
        if (service.deleteEmployee(id)) {
            log.info("Employee successfully deleted!");
            return new ResponseEntity<>("Employee successfully deleted!", HttpStatus.OK);
        } else {
            log.error("Employee with given id does not exist!");
            return new ResponseEntity<>("Employee with given id does not exist!", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/employees")
    public ResponseEntity<?> handleUpdateEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        try {
            Employee employeeToBeUpdated = ProjectModelMapper.convertDTOtoEmployee(employeeDTO);
            Employee updatedEmployee = service.updateEmployee(employeeToBeUpdated);
            log.info("Employee updated!");
            return new ResponseEntity<>(ProjectModelMapper.convertEmployeeToDTO(updatedEmployee), HttpStatus.OK);
        } catch (ValidationException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Server exception!");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/employees/{id}")
    public ResponseEntity<?> handleFindEmployeeById(@NotNull @PathVariable int id) {
        try {
            log.info("Employee found!");
            EmployeeDTO foundEmployee = ProjectModelMapper.convertEmployeeToDTO(service.findEmployeeByID(id));
            return new ResponseEntity<>(foundEmployee, HttpStatus.OK);
        } catch (ValidationException ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Server exception!");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
