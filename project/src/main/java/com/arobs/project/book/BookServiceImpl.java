package com.arobs.project.book;

import com.arobs.project.dtos.BookDTO;
import com.arobs.project.dtos.TagDTO;
import com.arobs.project.exception.ValidationException;
import com.arobs.project.mappers.ProjectModelMapper;
import com.arobs.project.tag.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    public List<BookDTO> findAllBooks() {
        log.info("Find all books...");
        return bookRepository.findAllBooks()
                .stream()
                .map(ProjectModelMapper::convertBookToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public BookDTO insertBook(BookDTO bookDTO) {
        log.info("Insert book...");
        Set<TagDTO> tagDTOS = bookDTO.getTags();
        insertNonexistentTags(tagDTOS);
        Book book = ProjectModelMapper.convertDTOtoBook(bookDTO);
        Book insertedBook = bookRepository.insertBook(book);
        bookDTO.setId(insertedBook.getId());
        return bookDTO;
    }

    private void insertNonexistentTags(Set<TagDTO> tagDTOS) {
        tagDTOS.forEach(tagDTO -> {
            TagDTO foundTag = tagService.findTagByDescription(tagDTO.getTagDescription());
            if (null == foundTag) {
                TagDTO createdTag = tagService.insertTag(tagDTO);
                tagDTO.setId(createdTag.getId());
            } else {
                tagDTO.setId(foundTag.getId());
            }
        });
    }

    @Override
    @Transactional
    public boolean deleteBook(int id) {
        log.info("Delete book...");
        Book foundBook = bookRepository.findBookById(id);
        if (foundBook != null) {
            return bookRepository.deleteBook(foundBook);
        }
        return false;
    }

    @Override
    @Transactional
    public BookDTO updateBook(BookDTO bookDTO) {
        log.info("Update book...");
        Book foundBook = bookRepository.findBookById(bookDTO.getId());
        if (foundBook != null) {
            Set<TagDTO> tagDTOS = bookDTO.getTags();
            insertNonexistentTags(tagDTOS);
            return ProjectModelMapper.convertBookToDTO(bookRepository.updateBook(ProjectModelMapper.convertDTOtoBook(bookDTO)));
        }
        return null;
    }

    @Override
    @Transactional
    public BookDTO findBookById(int id) throws ValidationException {
        log.info("Find book by id...");
        Book foundBook = bookRepository.findBookById(id);
        if (foundBook == null) {
            throw new ValidationException("Book with given id does not exist!");
        }
        return ProjectModelMapper.convertBookToDTO(foundBook);
    }

}
