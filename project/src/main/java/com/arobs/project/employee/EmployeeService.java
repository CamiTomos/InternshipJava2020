package com.arobs.project.employee;

import com.arobs.project.dtos.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDTO> findAllEmployees();

    EmployeeDTO insertEmployee(EmployeeDTO employeeDTO);

    boolean deleteEmployee(int id);

    EmployeeDTO updateEmployee(EmployeeDTO employeeDTO);

    EmployeeDTO findEmployeeByID(int id);
}
