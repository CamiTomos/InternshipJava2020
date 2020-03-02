package com.arobs.project.employee;

import com.arobs.project.dtos.EmployeeDTO;
import com.arobs.project.mappers.ProjectModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = hibernateRepository.getAllEmployees();
        List<EmployeeDTO> employeesDTO = new ArrayList<>(employees.size());
        for (Employee employee : employees) {
            employeesDTO.add(ProjectModelMapper.convertEmployeeToDTO(employee));
        }
        return employeesDTO;
    }

    @Override
    @Transactional
    public EmployeeDTO insertEmployee(EmployeeDTO employeeDTO) {
        Employee employee = ProjectModelMapper.convertDTOtoEmployee(employeeDTO);
        return ProjectModelMapper.convertEmployeeToDTO(hibernateRepository.insertEmployee(employee));
    }

    @Override
    @Transactional
    public boolean deleteEmployee(int id) {
        Employee employee=hibernateRepository.findById(id);
        return hibernateRepository.deleteEmployee(employee);
    }

    @Override
    @Transactional
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO) {
        Employee employee = ProjectModelMapper.convertDTOtoEmployee(employeeDTO);
        if(findEmployeeByID(employee.getId())!=null){
            return ProjectModelMapper.convertEmployeeToDTO(hibernateRepository.updateEmployee(employee));
        }
        return null;
    }

    @Override
    @Transactional
    public EmployeeDTO findEmployeeByID(int id) {
        return ProjectModelMapper.convertEmployeeToDTO(hibernateRepository.findById(id));
    }

}
