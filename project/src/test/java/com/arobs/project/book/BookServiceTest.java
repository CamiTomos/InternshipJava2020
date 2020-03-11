package com.arobs.project.book;

import com.arobs.project.dtos.BookDTO;
import com.arobs.project.dtos.TagDTO;
import com.arobs.project.mappers.ProjectModelMapper;
import com.arobs.project.tag.Tag;
import com.arobs.project.tag.TagService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @InjectMocks
    BookServiceImpl bookService;
    @Mock
    BookHibernateRepository bookHibernateRepository;
    @Mock
    TagService tagService;

    @BeforeAll
    static void setup() {
    }

    @Test
    void whenInsertBook_givenBookDTO_returnBookDTO() {
        //does not work!!!
        Set<TagDTO> tagDTOS = new HashSet<>(10);
        tagDTOS.add(new TagDTO(1, "keep working"));
        BookDTO bookDTO = new BookDTO(1, "a", "a", "a", tagDTOS);

        Set<Tag> tagsIn = new HashSet<>(10);
        tagsIn.add(new Tag(0, "keep working"));
        Set<Tag> tagsOut = new HashSet<>(10);
        tagsOut.add(new Tag(1, "keep working"));
        Book bookIn = new Book(0, "a", "a", "a",tagsIn);
        Book bookOut = new Book(1, "a", "a", "a",tagsOut);
        when(bookHibernateRepository.insertBook(bookIn)).thenReturn(bookOut);
        BookDTO insertedBook = bookService.insertBook(ProjectModelMapper.convertBookToDTO(bookOut));
        assertEquals(insertedBook,bookDTO);
    }
}
