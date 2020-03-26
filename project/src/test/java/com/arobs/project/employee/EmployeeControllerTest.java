package com.arobs.project.employee;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private EmployeeService employeeService;

    static List<Employee> employees = new ArrayList<>(10);

    @BeforeAll
    static void setup() {
        employees.add(new Employee(1, "ana", "admin", "ana", "ana@a.com", true, new Timestamp(System.currentTimeMillis())));
        employees.add(new Employee(2, "ion", "user", "ion", "ion@i.com", false, new Timestamp(System.currentTimeMillis())));
    }

    @Test
    void whenHandleFindAllEmployees_given_returnResponseEntity() {
        when(employeeService.findAllEmployees()).thenReturn(employees);
        ResponseEntity responseEntity = employeeController.handleFindAllEmployees();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }


}
