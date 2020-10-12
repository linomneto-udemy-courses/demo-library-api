package com.linomneto.demolibraryapi.api.resource.api.resource;

import com.linomneto.demolibraryapi.api.resource.api.dto.BookDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDTO create(@RequestBody BookDTO book) {
        book.setId(1L);
        return book;
    }

}
