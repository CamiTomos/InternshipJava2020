package com.arobs.project.mappers;

import com.arobs.project.book.Book;
import com.arobs.project.bookRent.BookRent;
import com.arobs.project.bookRequest.BookRequest;
import com.arobs.project.copy.Copy;
import com.arobs.project.dtos.*;
import com.arobs.project.employee.Employee;
import com.arobs.project.tag.Tag;

import java.text.SimpleDateFormat;

public class ProjectModelMapper {
    private static SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static org.modelmapper.ModelMapper modelMapper = new org.modelmapper.ModelMapper();

    public static BookDTO convertBookToDTO(Book book) {
        return modelMapper.map(book, BookDTO.class);
    }

    public static Book convertDTOtoBook(BookDTO bookDTO) {
        return modelMapper.map(bookDTO, Book.class);
    }

    public static EmployeeDTO convertEmployeeToDTO(Employee employee) {
        return modelMapper.map(employee, EmployeeDTO.class);
    }

    public static Employee convertDTOtoEmployee(EmployeeDTO employeeDTO) {
        return modelMapper.map(employeeDTO, Employee.class);
    }

    public static TagDTO convertTagToDTO(Tag tag) {
        return modelMapper.map(tag, TagDTO.class);
    }

    public static Tag convertDTOtoTag(TagDTO tagDTO) {
        return modelMapper.map(tagDTO, Tag.class);
    }

    public static CopyDTO convertCopyToDTO(Copy copy) {
        return modelMapper.map(copy, CopyDTO.class);
    }

    public static Copy convertDTOtoCopy(CopyDTO copyDTO) {
        return modelMapper.map(copyDTO, Copy.class);
    }

    public static BookRequestDTO convertBookRequestToDTO(BookRequest bookRequest) {
        return modelMapper.map(bookRequest, BookRequestDTO.class);
    }

    public static BookRequest convertDTOtoBookRequest(BookRequestDTO bookRequestDTO) {
        return modelMapper.map(bookRequestDTO, BookRequest.class);
    }

    //    public static BookRent convertDTOtoBookRent(BookRentDTO bookRentDTO) throws ParseException {
//        return new BookRent(bookRentDTO.getId()
//                new Timestamp(myFormat.parse(bookRentDTO.getBookrentRentalDate()).getTime()),
//                new Timestamp(myFormat.parse(bookRentDTO.getBookrentReturnDate()).getTime()),
//                bookRentDTO.getBookrentStatus(),
//                bookRentDTO.getBookrentNote()
//                bookRentDTO.getEmployee(),
//                bookRentDTO.getCopy()
//                );
//    }
    public static BookRentDTO convertBookRentToDTO(BookRent bookRent) {
        return new BookRentDTO(bookRent.getId());
    }
}
