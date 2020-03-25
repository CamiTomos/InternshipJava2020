//package com.arobs.project.employee;
//
//import com.arobs.project.dtos.EmployeeDTO;
//import com.arobs.project.mappers.ProjectModelMapper;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class EmployeeServiceTest {
//    @InjectMocks
//    private EmployeeServiceImpl employeeService;
//
//    @Mock
//    private EmployeeHibernateRepository employeeHibernateRepository;
//
//    @BeforeAll
//    static void setup() {
//    }
//
//    @Test
//    void whenInsertEmployee_givenEmployeeDTO_returnEmployeeDTO() {
//        EmployeeDTO employeeDTO = new EmployeeDTO(0, "a", "admin", "a", "a");
//        Employee employee = new Employee(1, "a", "admin", "a", "a");
//        when(employeeHibernateRepository.insertEmployee(ProjectModelMapper.convertDTOtoEmployee(employeeDTO))).thenReturn(employee);
//        EmployeeDTO insertedEmployee = employeeService.insertEmployee(employeeDTO);
//        assertEquals(ProjectModelMapper.convertEmployeeToDTO(employee), insertedEmployee);
//    }
//}
