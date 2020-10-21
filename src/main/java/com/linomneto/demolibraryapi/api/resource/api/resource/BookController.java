package com.linomneto.demolibraryapi.api.resource.api.resource;

import com.linomneto.demolibraryapi.api.resource.api.dto.BookDTO;
import com.linomneto.demolibraryapi.api.resource.api.model.Book;
import com.linomneto.demolibraryapi.api.resource.api.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final ModelMapper mapper;
    private BookService service;

    public BookController(BookService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDTO create(@RequestBody BookDTO dto) {
        Book book = mapper.map(dto, Book.class);
        book = service.save(book);
        BookDTO saved_dto = mapper.map(book, BookDTO.class);

        return saved_dto;
    }

}
