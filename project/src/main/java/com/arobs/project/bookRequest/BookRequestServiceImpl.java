package com.arobs.project.bookRequest;

import com.arobs.project.dtos.BookRequestDTO;
import com.arobs.project.dtos.EmployeeDTO;
import com.arobs.project.employee.EmployeeService;
import com.arobs.project.enums.BookRequestStatus;
import com.arobs.project.exception.ValidationException;
import com.arobs.project.mappers.ProjectModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("bookRequestServiceImpl")
@EnableTransactionManagement
public class BookRequestServiceImpl implements BookRequestService {
    private BookRequestHibernateRepository bookRequestRepository;
    private EmployeeService employeeService;

    @Autowired
    public BookRequestServiceImpl(BookRequestHibernateRepository bookRequestRepository, EmployeeService employeeService) {
        this.bookRequestRepository = bookRequestRepository;
        this.employeeService = employeeService;
    }

    @Override
    @Transactional
    public List<BookRequestDTO> findAllBookRequests() {
        List<BookRequest> bookRequests = bookRequestRepository.findAllBookRequests();
        List<BookRequestDTO> bookRequestDTOS = new ArrayList<>(bookRequests.size());
        for (BookRequest bookRequest : bookRequests) {
            bookRequestDTOS.add(ProjectModelMapper.convertBookRequestToDTO(bookRequest));
        }
        return bookRequestDTOS;
    }

    @Override
    @Transactional
    public BookRequestDTO insertBookRequest(BookRequestDTO bookRequestDTO) throws ValidationException {
        EmployeeDTO employeeDTO = employeeService.findEmployeeByID(bookRequestDTO.getEmployeeId());
        if (employeeDTO == null) {
            throw new ValidationException("The given employee does not exist!");
        }
        if (bookRequestDTO.getBookrequestStatus().toUpperCase().compareTo(String.valueOf(BookRequestStatus.PENDING)) != 0) {
            throw new ValidationException("Status must be pending!");
        }
        return ProjectModelMapper.convertBookRequestToDTO(bookRequestRepository.insertBookRequest(ProjectModelMapper.convertDTOtoBookRequest(bookRequestDTO)));
    }

    @Override
    @Transactional
    public boolean deleteBookRequest(int id) {
        BookRequest foundBookRequest = bookRequestRepository.findBookRequestById(id);
        if (foundBookRequest == null) {
            return false;
        }
        return bookRequestRepository.deleteBookRequest(foundBookRequest);
    }

    @Override
    @Transactional
    public BookRequestDTO updateBookRequest(BookRequestDTO bookRequestDTO) throws ValidationException {
        BookRequest foundBookRequest = bookRequestRepository.findBookRequestById(bookRequestDTO.getId());
        if (foundBookRequest == null) {
            return null;
        }
        String givenStatus = bookRequestDTO.getBookrequestStatus().toUpperCase();
        if (givenStatus.compareTo(String.valueOf(BookRequestStatus.PENDING)) != 0 &&
                givenStatus.compareTo(String.valueOf(BookRequestStatus.ACCEPTED)) != 0 &&
                givenStatus.compareTo(String.valueOf(BookRequestStatus.REJECTED)) != 0) {
            throw new ValidationException("Status must be pending or accepted or rejected!");
        }
        return ProjectModelMapper.convertBookRequestToDTO(bookRequestRepository.updateBookRequest(ProjectModelMapper.convertDTOtoBookRequest(bookRequestDTO)));
    }

    @Override
    @Transactional
    public BookRequestDTO findBookRequestById(int id) {
        BookRequest foundBookRequest = bookRequestRepository.findBookRequestById(id);
        if (null == foundBookRequest) {
            return null;
        }
        return ProjectModelMapper.convertBookRequestToDTO(foundBookRequest);
    }
}
