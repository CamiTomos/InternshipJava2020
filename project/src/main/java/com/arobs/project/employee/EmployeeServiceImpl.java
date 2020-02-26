package com.arobs.project.employee;

import com.arobs.project.dtos.EmployeeDTO;
import com.arobs.project.mappers.ProjectModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("employeeServiceImpl")
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeHibernateRepository hibernateRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeHibernateRepository hibernateRepository) {
        this.hibernateRepository = hibernateRepository;
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = hibernateRepository.getAllEmployees();
        List<EmployeeDTO> employeesDTO = new ArrayList<>(employees.size());
        for (Employee employee : employees
        ) {
            employeesDTO.add(ProjectModelMapper.convertEmployeeToDTO(employee));
        }
        return employeesDTO;
    }

    @Override
    public EmployeeDTO insertEmployee(EmployeeDTO employeeDTO) {
        Employee employee = ProjectModelMapper.convertDTOtoEmployee(employeeDTO);
        return ProjectModelMapper.convertEmployeeToDTO(hibernateRepository.insertEmployee(employee));
    }

    @Override
    public boolean deleteEmployee(int id) {
        return hibernateRepository.deleteEmployee(id);
    }

    @Override
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO) {
        Employee employee = ProjectModelMapper.convertDTOtoEmployee(employeeDTO);
        return ProjectModelMapper.convertEmployeeToDTO(hibernateRepository.updateEmployee(employee));
    }

    @Override
    public EmployeeDTO findEmployeeByID(int id) {
        return ProjectModelMapper.convertEmployeeToDTO(hibernateRepository.findById(id));
    }

}
