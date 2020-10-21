package com.linomneto.demolibraryapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.linomneto.demolibraryapi.dto.BookDTO;
import com.linomneto.demolibraryapi.model.Book;
import com.linomneto.demolibraryapi.repository.BookRepository;
import com.linomneto.demolibraryapi.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest
@AutoConfigureMockMvc
public class BookControllerTest {

    static String BOOK_API = "/api/books";

    @Autowired
    MockMvc mvc;

    @MockBean
    BookService service;

    @MockBean
    BookRepository repository;

    ModelMapper modelMapper;

    @BeforeEach
    public void setup() {
        this.modelMapper = new ModelMapper();
    }

    @Test
    @DisplayName("must create a book with success")
    public void createBookWithSuccessTest() throws Exception {
        BookDTO dto = BookDTO.builder().title("Foundation").author("Isaac Asimov").isbn("978-85-7657-066-0").build();
        Book expectedSavedBook = modelMapper.map(dto, Book.class);
        expectedSavedBook.setId(101L);

        BDDMockito.given(service.save(Mockito.any(Book.class))).willReturn(expectedSavedBook);
        String json = new ObjectMapper().writeValueAsString(dto);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
            .post(BOOK_API)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(json);

        mvc.perform(request)
            .andExpect(status().isCreated())
            .andExpect(jsonPath("id").value(expectedSavedBook.getId()))
            .andExpect(jsonPath("title").value(expectedSavedBook.getTitle()))
            .andExpect(jsonPath("author").value(expectedSavedBook.getAuthor()))
            .andExpect(jsonPath("isbn").value(expectedSavedBook.getIsbn()))
        ;
    }

    @Test
    @DisplayName("must not create a book because the mandatory fields were not sent")
    public void tryCreateBookAndFailTest()  throws Exception {
        String json = new ObjectMapper().writeValueAsString(new BookDTO());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
            .post(BOOK_API)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(json);

        mvc.perform(request)
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("errors", hasSize(3)));
    }

}
