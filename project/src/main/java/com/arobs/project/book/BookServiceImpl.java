package com.arobs.project.book;

import com.arobs.project.exception.ValidationException;
import com.arobs.project.tag.Tag;
import com.arobs.project.tag.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service("bookService")
@EnableTransactionManagement
public class BookServiceImpl implements BookService {
    private BookHibernateRepository bookRepository;
    private TagService tagService;
    private final Logger log = LoggerFactory.getLogger("FILE");

    @Autowired
    public BookServiceImpl(BookHibernateRepository bookRepository, TagService tagService) {
        this.bookRepository = bookRepository;
        this.tagService = tagService;
    }

    @Override
    @Transactional
    public List<Book> findAllBooks() {
        log.info("Find all books...");
        return bookRepository.findAllBooks();
    }

    @Override
    @Transactional
    public Book insertBook(Book book) {
        log.info("Insert book...");
        Set<Tag> tags = book.getTags();
        insertNonexistentTags(tags);
        return bookRepository.insertBook(book);
    }

    private void insertNonexistentTags(Set<Tag> tags) {
        for (Tag tag : tags) {
            try {
                Tag foundTag = tagService.findTagByDescription(tag.getTagDescription());
                tag.setId(foundTag.getId());
            } catch (ValidationException e) {
                Tag createdTag = tagService.insertTag(tag);
                tag.setId(createdTag.getId());
            }
        }
    }

    @Override
    @Transactional
    public boolean deleteBook(int id) {
        log.info("Delete book...");
        Book foundBook = bookRepository.findBookById(id);
        if (null == foundBook) {
            return false;
        }
        return bookRepository.deleteBook(foundBook);
    }

    @Override
    @Transactional
    public Book updateBook(Book book) throws ValidationException {
        log.info("Update book...");
        Book foundBook = bookRepository.findBookById(book.getId());
        if (null == foundBook) {
            throw new ValidationException("Book could not be updated!");
        }
        Set<Tag> tags = book.getTags();
        insertNonexistentTags(tags);
        return bookRepository.updateBook(book);

    }

    @Override
    @Transactional
    public Book findBookById(int id) throws ValidationException {
        log.info("Find book by id...");
        Book foundBook = bookRepository.findBookById(id);
        if (null == foundBook) {
            throw new ValidationException("Book with given id does not exist!");
        }
        return foundBook;
    }

}
