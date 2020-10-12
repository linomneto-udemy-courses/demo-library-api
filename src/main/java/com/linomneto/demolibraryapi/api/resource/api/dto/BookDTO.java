package com.linomneto.demolibraryapi.api.resource.api.dto;

import lombok.*;

@Getter
@Setter
@Builder
public class BookDTO {

    private Long  id;
    private String title;
    private String author;
    private String isbn;

}
