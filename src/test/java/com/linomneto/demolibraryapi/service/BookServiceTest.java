package com.linomneto.demolibraryapi.service;

import com.linomneto.demolibraryapi.model.Book;
import com.linomneto.demolibraryapi.repository.BookRepository;
import com.linomneto.demolibraryapi.service.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class BookServiceTest {

    ModelMapper modelMapper;
    BookService service;

    @MockBean
    BookRepository repository;

    @BeforeEach
    public void setup() {
        this.service = new BookServiceImpl(repository);
        this.modelMapper = new ModelMapper();
    }

    @Test
    @DisplayName("must save a book with success")
    public void saveBookTest() {
        //scenario
        Book book = Book.builder().title("Foundation").author("Isaac Asimov").isbn("978-85-7657-066-0").build();
        Book expectedSavedBook = modelMapper.map(book, Book.class);
        expectedSavedBook.setId(101L);
        Mockito.when(repository.save(book)).thenReturn(expectedSavedBook);

        //execution
        Book savedBook = service.save(book);

        //verifications
        assertThat(savedBook.getId()).isEqualTo(expectedSavedBook.getId());
        assertThat(savedBook.getTitle()).isEqualTo(expectedSavedBook.getTitle());
        assertThat(savedBook.getAuthor()).isEqualTo(expectedSavedBook.getAuthor());
        assertThat(savedBook.getIsbn()).isEqualTo(expectedSavedBook.getIsbn());
    }

}
