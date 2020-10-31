package com.linomneto.demolibraryapi.controller;

import com.linomneto.demolibraryapi.exception.APIErrors;
import com.linomneto.demolibraryapi.dto.BookDTO;
import com.linomneto.demolibraryapi.exception.BusinessException;
import com.linomneto.demolibraryapi.model.Book;
import com.linomneto.demolibraryapi.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public BookDTO create(@RequestBody @Valid BookDTO dto) {
        Book book = mapper.map(dto, Book.class);
        book = service.save(book);
        BookDTO saved_dto = mapper.map(book, BookDTO.class);

        return saved_dto;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public APIErrors handleValidationExceptions(MethodArgumentNotValidException e) {
        return new APIErrors(e.getBindingResult());
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public APIErrors handleBusinessExceptions(BusinessException e) {
        return new APIErrors(e);
    }

}
