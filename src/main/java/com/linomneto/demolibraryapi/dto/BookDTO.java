package com.linomneto.demolibraryapi.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

    private Long id;

    @NotEmpty(message = "field title must be not empty")
    private String title;

    @NotEmpty(message = "field author must be not empty")
    private String author;

    @NotEmpty(message = "field isbn must be not empty")
    private String isbn;

}
