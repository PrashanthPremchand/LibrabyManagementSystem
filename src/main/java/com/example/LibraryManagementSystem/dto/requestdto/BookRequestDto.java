package com.example.LibraryManagementSystem.dto.requestdto;

import com.example.LibraryManagementSystem.enums.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookRequestDto {
    private String title;
    private Genre genre;
    private int noOfPages;
    private int price;
    private int authorId;
}

