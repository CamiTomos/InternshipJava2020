//package com.arobs.project.book;
//
//import com.arobs.project.dtos.BookDTO;
//import com.arobs.project.exception.ValidationException;
//import com.arobs.project.mappers.ProjectModelMapper;
//import com.arobs.project.tag.TagService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Matchers.any;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class BookServiceTest {
//    @InjectMocks
//    BookServiceImpl bookService;
//    @Mock
//    BookHibernateRepository bookHibernateRepository;
//    @Mock
//    TagService tagService;
//
//    @BeforeEach
//    void setup() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    void whenInsertBook_givenBookDTO_returnBookDTO() {
//        Book bookToInsert = new Book(0, "title", "author", "description");
//        Book insertedBook = bookToInsert;
//        insertedBook.setId(1);
//        when(bookHibernateRepository.insertBook(any(Book.class))).thenReturn(insertedBook);
//        BookDTO book = bookService.insertBook(ProjectModelMapper.convertBookToDTO(bookToInsert));
//        assertEquals(book, ProjectModelMapper.convertBookToDTO(insertedBook));
//    }
//
//    @Test
//    void whenDeleteBook_givenId_returnBoolean() {
//        when(bookHibernateRepository.deleteBook(any(Book.class))).thenReturn(true);
//        Boolean isDeleted = bookService.deleteBook(10);
//        assertEquals(true, isDeleted);
//    }
//
//    @Test
//    void whenUpdateBook_givenBookDTO_returnBookDTO() {
//        Book bookToUpdate = new Book(1, "title", "author", "description");
//        Book updatedBook = bookToUpdate;
//        when(bookHibernateRepository.findBookById(bookToUpdate.getId())).thenReturn(updatedBook);
//        when(bookHibernateRepository.updateBook(any(Book.class))).thenReturn(updatedBook);
//        BookDTO book = bookService.updateBook(ProjectModelMapper.convertBookToDTO(bookToUpdate));
//        assertEquals(ProjectModelMapper.convertBookToDTO(updatedBook), book);
//    }
//
//    @Test
//    void whenFindById_givenId_returnBookDTO() throws ValidationException {
//        Book foundBook = new Book(1, "title", "author", "description");
//        when(bookHibernateRepository.findBookById(foundBook.getId())).thenReturn(foundBook);
//        BookDTO book = bookService.findBookById(foundBook.getId());
//        assertEquals(ProjectModelMapper.convertBookToDTO(foundBook), book);
//    }
//}
