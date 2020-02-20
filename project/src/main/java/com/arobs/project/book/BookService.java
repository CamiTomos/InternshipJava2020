package com.arobs.project.book;

import com.arobs.project.dtos.BookDTO;
import com.arobs.project.mappers.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service("bookService")
public class BookService {
    private BookJDBCRepository bookRepository;

    @Autowired
    public BookService(BookJDBCRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookDTO> getAllBooks(){
        List<Book> books=bookRepository.getAllBooks();
        List<BookDTO> bookDTOS=new ArrayList<>(books.size());
        for (Book book:books
             ) {
            bookDTOS.add(new BookDTO(book.getBookTitle(),book.getBookAuthor(),book.getBookDescription(), book.getBookAddedDate().toString()));
        }
        return bookDTOS;
    }

    public BookDTO insertBook(BookDTO bookDTO){
        System.out.println("Data " +bookDTO.getBookAddedDate());
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Book book= null;
        try {
            book = new Book(bookDTO.getBookTitle(),bookDTO.getBookAuthor(),bookDTO.getBookDescription(),new Timestamp(myFormat.parse(bookDTO.getBookAddedDate()).getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        bookRepository.insertBook(book);
        return bookDTO;
    }

    public void deleteBook(int id){
        bookRepository.deleteBook(id);
    }

    public BookDTO updateBook(BookDTO bookDTO, int id){
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Book book= null;
        try {
            book = new Book(id,bookDTO.getBookTitle(),bookDTO.getBookAuthor(),bookDTO.getBookDescription(),new Timestamp(myFormat.parse(bookDTO.getBookAddedDate()).getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        bookRepository.updateBook(book);
        return bookDTO;
    }

    public BookDTO findById(int id){
        return ModelMapper.convertBookToDTO(bookRepository.findById(id));
    }

}
