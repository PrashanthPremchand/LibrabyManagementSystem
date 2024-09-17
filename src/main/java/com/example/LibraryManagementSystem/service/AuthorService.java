package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.dto.responsedto.AuthorResponseDto;
import com.example.LibraryManagementSystem.entity.Author;
import com.example.LibraryManagementSystem.expections.AuthorNotFoundException;

public interface AuthorService {
    public String addAuthor(Author author);
    public AuthorResponseDto getAuthorByEmail(String email) throws AuthorNotFoundException;
}
