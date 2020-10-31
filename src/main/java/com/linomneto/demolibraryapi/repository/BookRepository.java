package com.linomneto.demolibraryapi.repository;

import com.linomneto.demolibraryapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    public Book save(Book book);
    public Boolean existsByIsbn(String isbn);
}
