package com.linomneto.demolibraryapi.api.resource.repository;

import com.linomneto.demolibraryapi.api.resource.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    public Book save(Book book);
}
