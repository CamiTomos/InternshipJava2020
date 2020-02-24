package com.arobs.project.book;

import com.arobs.project.dtos.BookDTO;
import com.arobs.project.mappers.ProjectModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service("bookService")
public class BookServiceImpl implements BookService {
    private BookJDBCRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookJDBCRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional
    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.getAllBooks();
        List<BookDTO> bookDTOS = new ArrayList<>(books.size());
        for (Book book : books
        ) {
            bookDTOS.add(ProjectModelMapper.convertBookToDTO(book));
        }
        return bookDTOS;
    }

    @Override
    @Transactional
    public BookDTO insertBook(BookDTO bookDTO) throws ParseException {
        Book book = ProjectModelMapper.convertDTOtoBook(bookDTO);
        Book insertedBook = bookRepository.insertBook(book);
        bookDTO.setId(insertedBook.getId());
        return bookDTO;
    }

    @Override
    @Transactional
    public boolean deleteBook(int id) {
        return bookRepository.deleteBook(id);
    }

    @Override
    @Transactional
    public BookDTO updateBook(BookDTO bookDTO) throws ParseException {
        Book book = ProjectModelMapper.convertDTOtoBook(bookDTO);
        return ProjectModelMapper.convertBookToDTO(bookRepository.updateBook(book));
    }

    @Override
    @Transactional
    public BookDTO findById(int id) {
        return ProjectModelMapper.convertBookToDTO(bookRepository.findById(id));
    }

}
