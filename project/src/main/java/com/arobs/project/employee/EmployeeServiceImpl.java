package com.arobs.project.employee;

import com.arobs.project.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public List<Employee> findAllEmployees() {
        return hibernateRepository.findAllEmployees();
    }

    @Override
    @Transactional
    public Employee insertEmployee(Employee employee) {
        return hibernateRepository.insertEmployee(employee);
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
    public Employee updateEmployee(Employee employee) throws ValidationException {
        Employee foundEmployee = hibernateRepository.findById(employee.getId());
        if (null == foundEmployee) {
            throw new ValidationException("The given employee does not exist!");
        }
        return hibernateRepository.updateEmployee(employee);
    }

    @Override
    @Transactional
    public Employee findEmployeeByID(int id) throws ValidationException {
        Employee foundEmployee = hibernateRepository.findById(id);
        if (null == foundEmployee) {
            throw new ValidationException("The employee with given id does not exist!");
        }
        return foundEmployee;
    }
}
