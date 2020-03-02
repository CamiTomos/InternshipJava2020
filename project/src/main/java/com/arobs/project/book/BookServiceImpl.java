package com.arobs.project.book;

import com.arobs.project.dtos.BookDTO;
import com.arobs.project.dtos.TagDTO;
import com.arobs.project.mappers.ProjectModelMapper;
import com.arobs.project.tag.Tag;
import com.arobs.project.tag.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service("bookService")
@EnableTransactionManagement
public class BookServiceImpl implements BookService {
    //    private BookJDBCRepository bookRepository;
    private BookHibernateRepository bookRepository;
    private TagService tagService;

    //    @Autowired
//    public BookServiceImpl(BookJDBCRepository bookRepository) {
//        this.bookRepository = bookRepository;
//    }
    @Autowired
    public BookServiceImpl(BookHibernateRepository bookRepository, TagService tagService) {
        this.bookRepository = bookRepository;
        this.tagService = tagService;
    }

//    @Override
//    @Transactional
//    public List<BookDTO> getAllBooks() {
//        List<Book> books = bookRepository.getAllBooks();
//        List<BookDTO> bookDTOS = new ArrayList<>(books.size());
//        for (Book book : books
//        ) {
//            bookDTOS.add(ProjectModelMapper.convertBookToDTO(book));
//        }
//        return bookDTOS;
//    }

    @Override
    @Transactional
    public BookDTO insertBook(BookDTO bookDTO) throws ParseException {
        Book book = ProjectModelMapper.convertDTOtoBook(bookDTO);
        Book insertedBook = bookRepository.insertBook(book);
//        bookDTO.setId(insertedBook.getId());
//        Set<TagDTO> tagDTOS=bookDTO.getTags();
//        for (TagDTO tagDTO:tagDTOS){
//            tagService.insertTag(tagDTO);
//        }
        return bookDTO;
    }

    @Override
    @Transactional
    public boolean deleteBook(int id) {
        Book book=bookRepository.findById(id);
        if(book!=null){
            return bookRepository.deleteBook(book);
        }
        return false;
    }

//    @Override
//    @Transactional
//    public BookDTO updateBook(BookDTO bookDTO) throws ParseException {
//        Book book = ProjectModelMapper.convertDTOtoBook(bookDTO);
//        return ProjectModelMapper.convertBookToDTO(bookRepository.updateBook(book));
//    }
//
//    @Override
//    @Transactional
//    public BookDTO findById(int id) {
//        return ProjectModelMapper.convertBookToDTO(bookRepository.findById(id));
//    }

}
