package com.example.LibraryManagementSystem.controller;

import com.example.LibraryManagementSystem.dto.requestdto.BookRequestDto;
import com.example.LibraryManagementSystem.dto.responsedto.FindAllBookOfGenreResponseDto;
import com.example.LibraryManagementSystem.dto.responsedto.FindAuthorsNameOfAllBooksByGenreResponseDto;
import com.example.LibraryManagementSystem.entity.Book;
import com.example.LibraryManagementSystem.enums.Genre;
import com.example.LibraryManagementSystem.expections.BookNotFoundException;
import com.example.LibraryManagementSystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService bookService;

    @PostMapping("/add")
    public String addBook(@RequestBody BookRequestDto bookRequestDto) throws Exception {
        return bookService.addBook(bookRequestDto);
    }

    @GetMapping("/get_by_genre")
    public FindAllBookOfGenreResponseDto getByGenre(@RequestParam("genre") Genre genre){
        return bookService.getByGenre(genre);
    }
    @GetMapping("/get_authors_by_genre")
    public FindAuthorsNameOfAllBooksByGenreResponseDto getAuthorNamesByGenre(@RequestParam("genre") Genre genre){
        return  bookService.getAuthorNamesByGenre(genre);
    }

    @DeleteMapping("/delete_book/{id}")
    public String deleteBook(@PathVariable("id") int id) throws BookNotFoundException {
        return bookService.deleteBook(id);
    }
}
