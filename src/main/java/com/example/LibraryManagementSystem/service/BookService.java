package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.dto.requestdto.BookRequestDto;
import com.example.LibraryManagementSystem.dto.responsedto.FindAllBookOfGenreResponseDto;
import com.example.LibraryManagementSystem.dto.responsedto.FindAuthorsNameOfAllBooksByGenreResponseDto;
import com.example.LibraryManagementSystem.entity.Book;
import com.example.LibraryManagementSystem.enums.Gender;
import com.example.LibraryManagementSystem.enums.Genre;
import com.example.LibraryManagementSystem.expections.BookNotFoundException;

public interface BookService {
    public String addBook(BookRequestDto bookRequestDto) throws Exception;
    public FindAllBookOfGenreResponseDto getByGenre(Genre genre);
    public FindAuthorsNameOfAllBooksByGenreResponseDto getAuthorNamesByGenre(Genre genre);
    public String deleteBook(int id) throws BookNotFoundException;
}
