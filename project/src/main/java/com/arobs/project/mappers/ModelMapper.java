package com.arobs.project.mappers;

import com.arobs.project.book.Book;
import com.arobs.project.dtos.BookDTO;

public class ModelMapper {
    private static org.modelmapper.ModelMapper modelMapper = new org.modelmapper.ModelMapper();
    public static BookDTO convertBookToDTO(Book book){
        BookDTO employeeDTO= modelMapper.map(book,BookDTO.class);
        return employeeDTO;
    }
}
