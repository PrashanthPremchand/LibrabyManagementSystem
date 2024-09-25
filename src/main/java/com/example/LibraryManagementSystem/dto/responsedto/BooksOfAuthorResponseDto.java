package com.example.LibraryManagementSystem.dto.responsedto;

import com.example.LibraryManagementSystem.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BooksOfAuthorResponseDto {
    private List<String> books = new ArrayList<>();
}
