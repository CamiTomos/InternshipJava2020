package com.arobs.project.bookRequest;

import com.arobs.project.dtos.BookRequestDTO;
import com.arobs.project.dtos.EmployeeDTO;
import com.arobs.project.employee.EmployeeService;
import com.arobs.project.exception.ValidationException;
import com.arobs.project.mappers.ProjectModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;
import java.util.List;

@Service("bookRequestServiceImpl")
@EnableTransactionManagement
public class BookRequestServiceImpl implements BookRequestService {
    private BookRequestHibernateRepository hibernateRepository;
    private EmployeeService employeeService;

    @Autowired
    public BookRequestServiceImpl(BookRequestHibernateRepository hibernateRepository, EmployeeService employeeService) {
        this.hibernateRepository = hibernateRepository;
        this.employeeService = employeeService;
    }

    @Override
    public List<BookRequestDTO> getAllBookRequests() {
        List<BookRequest> bookRequests = hibernateRepository.getAllBookRequests();
        List<BookRequestDTO> bookRequestDTOS = new ArrayList<>(bookRequests.size());
        for (BookRequest bookRequest : bookRequests) {
            bookRequestDTOS.add(ProjectModelMapper.convertBookRequestToDTO(bookRequest));
        }
        return bookRequestDTOS;
    }

    @Override
    public BookRequestDTO insertBookRequest(BookRequestDTO bookRequestDTO) throws ValidationException {
        EmployeeDTO employeeDTO = employeeService.findEmployeeByID(bookRequestDTO.getEmployeeId());
        if (employeeDTO == null) {
            throw new ValidationException("The given employee does not exist!");
        }
        // to be continued
        return null;
    }

    @Override
    public boolean deleteBookRequest(int id) {
        return false;
    }

    @Override
    public BookRequestDTO updateBookRequest(BookRequestDTO bookRequestDTO) {
        return null;
    }

    @Override
    public BookRequestDTO findBookRequestById(int id) {
        return null;
    }
}
