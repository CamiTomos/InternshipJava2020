package com.arobs.project.book;

import com.arobs.project.dtos.BookDTO;
import com.arobs.project.dtos.TagDTO;
import com.arobs.project.mappers.ProjectModelMapper;
import com.arobs.project.tag.TagService;
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

    @Autowired
    public BookServiceImpl(BookHibernateRepository bookRepository, TagService tagService) {
        this.bookRepository = bookRepository;
        this.tagService = tagService;
    }

    @Override
    @Transactional
    public List<BookDTO> findAllBooks() {
        return bookRepository.findAllBooks()
                .stream()
                .map(ProjectModelMapper::convertBookToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public BookDTO insertBook(BookDTO bookDTO) {
        Set<TagDTO> tagDTOS = bookDTO.getTags();
        for (TagDTO tagDTO : tagDTOS) {
            TagDTO foundTag = tagService.findTagByDescription(tagDTO.getTagDescription());
            if (null == foundTag) {
                TagDTO createdTag = tagService.insertTag(tagDTO);
                tagDTO.setId(createdTag.getId());
            } else {
                tagDTO.setId(foundTag.getId());
            }
        }
        Book book = ProjectModelMapper.convertDTOtoBook(bookDTO);
        Book insertedBook = bookRepository.insertBook(book);
        bookDTO.setId(insertedBook.getId());
        return bookDTO;
    }

    @Override
    @Transactional
    public boolean deleteBook(int id) {
        Book foundBook = bookRepository.findBookById(id);
        if (foundBook != null) {
            return bookRepository.deleteBook(foundBook);
        }
        return false;
    }

    @Override
    @Transactional
    public BookDTO updateBook(BookDTO bookDTO) {
        Book foundBook = bookRepository.findBookById(bookDTO.getId());
        if (foundBook != null) {
            Set<TagDTO> tagDTOS = bookDTO.getTags();
            for (TagDTO tagDTO : tagDTOS) {
                TagDTO foundTag = tagService.findTagByDescription(tagDTO.getTagDescription());
                if (null == foundTag) {
                    TagDTO createdTag = tagService.insertTag(tagDTO);
                    tagDTO.setId(createdTag.getId());
                } else {
                    tagDTO.setId(foundTag.getId());
                }
            }
            return ProjectModelMapper.convertBookToDTO(bookRepository.updateBook(ProjectModelMapper.convertDTOtoBook(bookDTO)));
        }
        return null;
    }

    @Override
    @Transactional
    public BookDTO findById(int id) {
        Book foundBook = bookRepository.findBookById(id);
        if (foundBook != null) {
            return ProjectModelMapper.convertBookToDTO(foundBook);
        }
        return null;
    }

}
