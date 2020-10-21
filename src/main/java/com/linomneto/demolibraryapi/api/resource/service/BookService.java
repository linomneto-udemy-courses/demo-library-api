package com.linomneto.demolibraryapi.api.resource.service;

import com.linomneto.demolibraryapi.api.resource.dto.BookDTO;
import com.linomneto.demolibraryapi.api.resource.model.Book;

public interface BookService {
    public Book save(Book book);
}
