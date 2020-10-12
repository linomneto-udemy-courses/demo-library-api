package com.linomneto.demolibraryapi.api.resource.api.resource;

import com.linomneto.demolibraryapi.api.resource.api.dto.BookDTO;
import com.linomneto.demolibraryapi.api.resource.api.model.Book;
import com.linomneto.demolibraryapi.api.resource.api.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDTO create(@RequestBody BookDTO dto) {
        Book book = service.save(dto);

        BookDTO saved_dto = BookDTO.builder()
            .id(book.getId())
            .title(book.getTitle())
            .author(book.getAuthor())
            .isbn(book.getIsbn())
            .build();

        return saved_dto;
    }

}
