package com.linomneto.demolibraryapi.service.impl;

import com.linomneto.demolibraryapi.model.Book;
import com.linomneto.demolibraryapi.repository.BookRepository;
import com.linomneto.demolibraryapi.service.BookService;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository repository;

    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public Book save(Book book) {
        return repository.save(book);
    }
}
