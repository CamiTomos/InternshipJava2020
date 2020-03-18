package com.arobs.project.bookRent;

import com.arobs.project.book.BookService;
import com.arobs.project.copy.CopyService;
import com.arobs.project.dtos.BookDTO;
import com.arobs.project.dtos.BookRentDTO;
import com.arobs.project.dtos.CopyDTO;
import com.arobs.project.dtos.EmployeeDTO;
import com.arobs.project.employee.EmployeeService;
import com.arobs.project.exception.ValidationException;
import com.arobs.project.mappers.ProjectModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@Service("bookRentServiceImpl")
@EnableTransactionManagement
public class BookRentServiceImpl implements BookRentService {
    private BookRentHibernateRepository bookRentRepository;
    private BookService bookService;
    private CopyService copyService;
    private EmployeeService employeeService;

    @Autowired
    public BookRentServiceImpl(BookRentHibernateRepository bookRentRepository, BookService bookService, CopyService copyService, EmployeeService employeeService) {
        this.bookRentRepository = bookRentRepository;
        this.bookService = bookService;
        this.copyService = copyService;
        this.employeeService = employeeService;
    }

    @Override
    @Transactional
    public BookRentDTO insertBookRent(BookRentDTO bookRentDTO) throws ParseException, ValidationException {
        BookDTO foundBook = bookService.findById(bookRentDTO.getBookId());
        EmployeeDTO foundEmployee = employeeService.findEmployeeByID(bookRentDTO.getEmployeeId());
        List<CopyDTO> foundCopiesForBook = copyService.findCopiesForBook(foundBook.getId());
        List<CopyDTO> foundAvailableCopies = foundCopiesForBook //is it better to do that from query?
                .stream()
                .filter(c -> c.getCopyStatus().equals("available"))
                .collect(Collectors.toList());
        if (foundAvailableCopies.isEmpty()) {
            throw new ValidationException("No available book found!");
        }
        BookRent bookRentToInsert = ProjectModelMapper.convertDTOtoBookRent(bookRentDTO);
        bookRentToInsert.setCopy(ProjectModelMapper.convertDTOtoCopy(foundAvailableCopies.get(0)));
        bookRentToInsert.setEmployee(ProjectModelMapper.convertDTOtoEmployee(foundEmployee));
        bookRentToInsert.setBook(ProjectModelMapper.convertDTOtoBook(foundBook));
        return ProjectModelMapper.convertBookRentToDTO(bookRentRepository.insertBookRent(bookRentToInsert));
    }
}
