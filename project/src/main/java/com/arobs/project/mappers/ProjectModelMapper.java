package com.arobs.project.mappers;

import com.arobs.project.book.Book;
import com.arobs.project.dtos.BookDTO;
import com.arobs.project.dtos.EmployeeDTO;
import com.arobs.project.dtos.TagDTO;
import com.arobs.project.employee.Employee;
import com.arobs.project.tag.Tag;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ProjectModelMapper {
    private static SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static org.modelmapper.ModelMapper modelMapper = new org.modelmapper.ModelMapper();

    public static BookDTO convertBookToDTO(Book book) {
//        return new BookDTO(book.getId(),
//                book.getBookTitle(),
//                book.getBookAuthor(),
//                book.getBookDescription(),
//                book.getBookAddedDate().toString());
        return modelMapper.map(book, BookDTO.class); //Doesn't work
    }

    public static Book convertDTOtoBook(BookDTO bookDTO)  {
//        return new Book(bookDTO.getId(),
//                bookDTO.getBookTitle(),
//                bookDTO.getBookAuthor(),
//                bookDTO.getBookDescription(),
//                new Timestamp(myFormat.parse(bookDTO.getBookAddedDate()).getTime()));
        return modelMapper.map(bookDTO, Book.class);
    }

    public static EmployeeDTO convertEmployeeToDTO(Employee employee) {
        return new EmployeeDTO(employee.getId(),
                employee.getEmployeeName(),
                employee.getEmployeeRole(),
                employee.getEmployeePassword(),
                employee.getEmployeeEmail()
        );
//        return modelMapper.map(book,BookDTO.class); Doesn't work
    }

    public static Employee convertDTOtoEmployee(EmployeeDTO employeeDTO) {
        return new Employee(employeeDTO.getId(),
                employeeDTO.getEmployeeName(),
                employeeDTO.getEmployeeRole(),
                employeeDTO.getEmployeePassword(),
                employeeDTO.getEmployeeEmail());
    }

    public static TagDTO convertTagToDTO(Tag tag) {
//        return new TagDTO(tag.getId(),
//                tag.getTagDescription()
//        );
        return modelMapper.map(tag, TagDTO.class);
    }

    public static Tag convertDTOtoTag(TagDTO tagDTO) {
//        return new Tag(tagDTO.getId(),
//                tagDTO.getTagDescription()
//        );
        return modelMapper.map(tagDTO, Tag.class);
    }
}
