package com.linomneto.demolibraryapi.api.resource.api.service;

import com.linomneto.demolibraryapi.api.resource.api.dto.BookDTO;
import com.linomneto.demolibraryapi.api.resource.api.model.Book;

public interface BookService {
    public Book save(BookDTO book);
}
