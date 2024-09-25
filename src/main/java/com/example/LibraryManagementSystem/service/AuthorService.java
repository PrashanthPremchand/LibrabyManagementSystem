package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.dto.requestdto.AuthorRequestDto;
import com.example.LibraryManagementSystem.dto.requestdto.UpdateAuthorAgeRequestDto;
import com.example.LibraryManagementSystem.dto.requestdto.UpdateAuthorEmailRequestDTO;
import com.example.LibraryManagementSystem.dto.requestdto.UpdateAuthorNameRequestDto;
import com.example.LibraryManagementSystem.dto.responsedto.*;
import com.example.LibraryManagementSystem.entity.Author;
import com.example.LibraryManagementSystem.expections.AuthorNotFoundException;

public interface AuthorService {
    public String addAuthor(AuthorRequestDto authorRequestDto);
    public AuthorResponseDto getAuthorByEmail(String email) throws AuthorNotFoundException;
    public BooksOfAuthorResponseDto getBooksOfAuthor(int id) throws AuthorNotFoundException;
    public FindAuthorWithMoreThanNumberOfBooks getAuthorsWithMoreThanNumberOfBooks(int numberOfBooks);
    public UpdateAuthorNameResponseDto updateName(UpdateAuthorNameRequestDto updateAuthorNameRequestDto) throws AuthorNotFoundException;
    public UpdateAuthorAgeResponseDto updateAge(UpdateAuthorAgeRequestDto updateAuthorAgeRequestDto) throws AuthorNotFoundException;
    public UpdateAuthorEmailResponseDto updateEmail(UpdateAuthorEmailRequestDTO updateAuthorEmailRequestDTO) throws AuthorNotFoundException;
}
