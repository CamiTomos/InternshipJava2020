package com.arobs.project.employee;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
    private EmployeeHibernateRepository employeeHibernateRepository;

    @BeforeAll
    static void setup() {
    }

    @Test
    void whenInsertEmployee_givenEmployeeDTO_returnEmployeeDTO() {
        Employee employee = new Employee(0, "a", "admin", "a", "a", true, new Timestamp(System.currentTimeMillis()));
        Employee insertedEmployee = new Employee(1, "a", "admin", "a", "a", true, new Timestamp(System.currentTimeMillis()));
        when(employeeHibernateRepository.insertEmployee(employee)).thenReturn(insertedEmployee);
        Employee insertedEmployeeService = employeeService.insertEmployee(insertedEmployee);
        assertEquals(insertedEmployee, insertedEmployeeService);
    }
}
