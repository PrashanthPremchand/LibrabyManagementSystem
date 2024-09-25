package com.example.LibraryManagementSystem.controller;

import com.example.LibraryManagementSystem.dto.requestdto.AuthorRequestDto;
import com.example.LibraryManagementSystem.dto.requestdto.UpdateAuthorAgeRequestDto;
import com.example.LibraryManagementSystem.dto.requestdto.UpdateAuthorEmailRequestDTO;
import com.example.LibraryManagementSystem.dto.requestdto.UpdateAuthorNameRequestDto;
import com.example.LibraryManagementSystem.dto.responsedto.*;
import com.example.LibraryManagementSystem.entity.Author;
import com.example.LibraryManagementSystem.expections.AuthorNotFoundException;
import com.example.LibraryManagementSystem.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    AuthorService authorService;

    @PostMapping("/add")
    public String addAuthor(@RequestBody AuthorRequestDto authorRequestDto){
        return authorService.addAuthor(authorRequestDto);
    }

    @GetMapping("/get_by_email")
    public AuthorResponseDto getAuthorByEmail(@RequestParam("email") String email) throws AuthorNotFoundException {
        return authorService.getAuthorByEmail(email);
    }
    @GetMapping("/get_books_author")
    public BooksOfAuthorResponseDto getBooksOfAuthor(@RequestParam("id") int id) throws AuthorNotFoundException {
        return authorService.getBooksOfAuthor(id);
    }
    @GetMapping("/get_authors_with_more_number_of_books_than/{numberOfBooks}")
    public FindAuthorWithMoreThanNumberOfBooks getAuthorsWithMoreThanNumberOfBooks(@PathVariable("numberOfBooks") int numberOfBooks){
        return authorService.getAuthorsWithMoreThanNumberOfBooks(numberOfBooks);
    }

    @PutMapping("/update_name")
    public UpdateAuthorNameResponseDto updateName(@RequestBody UpdateAuthorNameRequestDto updateAuthorNameRequestDto) throws AuthorNotFoundException {
        return authorService.updateName(updateAuthorNameRequestDto);
    }
    @PutMapping("/update_age")
    public UpdateAuthorAgeResponseDto updateAge(@RequestBody UpdateAuthorAgeRequestDto updateAuthorAgeRequestDto) throws AuthorNotFoundException {
        return authorService.updateAge(updateAuthorAgeRequestDto);
    }
    @PutMapping("/update_email")
    public UpdateAuthorEmailResponseDto updateEmail (@RequestBody UpdateAuthorEmailRequestDTO updateAuthorEmailRequestDTO) throws AuthorNotFoundException {
        return authorService.updateEmail(updateAuthorEmailRequestDTO);
    }


}
