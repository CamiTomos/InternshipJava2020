package com.arobs.project.employee;

import com.arobs.project.dtos.EmployeeDTO;
import com.arobs.project.exception.ValidationException;
import com.arobs.project.mappers.ProjectModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service("employeeServiceImpl")
@EnableTransactionManagement
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeHibernateRepository hibernateRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeHibernateRepository hibernateRepository) {
        this.hibernateRepository = hibernateRepository;
    }

    @Override
    @Transactional
    public List<EmployeeDTO> findAllEmployees() {
        return hibernateRepository.findAllEmployees()
                .stream()
                .map(ProjectModelMapper::convertEmployeeToDTO)
                .collect(Collectors.toList());
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
        Employee foundEmployee = hibernateRepository.findById(id);
        if (null == foundEmployee) {
            return false;
        }
        return hibernateRepository.deleteEmployee(foundEmployee);
    }

    @Override
    @Transactional
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO) {
        Employee foundEmployee = hibernateRepository.findById(employeeDTO.getId());
        if (foundEmployee == null) {
            return null;
        }
        return ProjectModelMapper.convertEmployeeToDTO(hibernateRepository.updateEmployee(ProjectModelMapper.convertDTOtoEmployee(employeeDTO)));
    }

    @Override
    @Transactional
    public EmployeeDTO findEmployeeByID(int id) throws ValidationException {
        Employee foundEmployee = hibernateRepository.findById(id);
        if (foundEmployee == null) {
            throw new ValidationException("The employee with given id does not exist!");
        }
        return ProjectModelMapper.convertEmployeeToDTO(foundEmployee);
    }

}
