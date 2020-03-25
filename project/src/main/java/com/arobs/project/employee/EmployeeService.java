package com.arobs.project.employee;

import com.arobs.project.exception.ValidationException;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAllEmployees();

    Employee insertEmployee(Employee employee);

    boolean deleteEmployee(int id);

    Employee updateEmployee(Employee employee) throws ValidationException;

    Employee findEmployeeByID(int id) throws ValidationException;
}
