package com.linomneto.demolibraryapi.service;

import com.linomneto.demolibraryapi.exception.BusinessException;
import com.linomneto.demolibraryapi.model.Book;
import com.linomneto.demolibraryapi.repository.BookRepository;
import com.linomneto.demolibraryapi.service.impl.BookServiceImpl;
import com.linomneto.demolibraryapi.utils.BookTestUtils;
import org.assertj.core.api.Assertions;
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
        Book book = BookTestUtils.newFoundationBook();
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

    @Test
    @DisplayName("must not create a book because the isbn field already exists")
    public void tryCreateBookWithExistentIsbnAndFailTest() {
        //scenario
        Book book = BookTestUtils.newFoundationBook();
        Mockito.when(repository.existsByIsbn(book.getIsbn())).thenReturn(true);

        //execution
        Throwable exception = Assertions.catchThrowable(() -> service.save(book));

        //verifications
        String errorMessage = BusinessException.Type.IsbnAlreadyExists.msg();

        assertThat(exception).isInstanceOf(BusinessException.class).hasMessage(errorMessage);
        Mockito.verify(repository, Mockito.never()).save(book);
    }

}
