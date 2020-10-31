package com.linomneto.demolibraryapi.utils;

import com.linomneto.demolibraryapi.dto.BookDTO;
import com.linomneto.demolibraryapi.model.Book;
import org.modelmapper.ModelMapper;

public class BookTestUtils {

    public static BookDTO newFoundationBookDTO() {
        BookDTO dto = BookDTO.builder().title("Foundation").author("Isaac Asimov").isbn("978-85-7657-066-0").build();
        return dto;
    }

    public static Book newFoundationBook() {
        BookDTO dto = BookTestUtils.newFoundationBookDTO();
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, Book.class);
    }

}
