package com.example.LibraryManagementSystem.service.impl;

import com.example.LibraryManagementSystem.dto.responsedto.AuthorResponseDto;
import com.example.LibraryManagementSystem.entity.Author;
import com.example.LibraryManagementSystem.expections.AuthorNotFoundException;
import com.example.LibraryManagementSystem.repository.AuthorRepository;
import com.example.LibraryManagementSystem.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    AuthorRepository authorRepository;
    @Override
    public String addAuthor(Author author) {
        authorRepository.save(author);
        return "Author added";
    }

    @Override
    public AuthorResponseDto getAuthorByEmail(String email) throws AuthorNotFoundException {
        try{
            Author author = authorRepository.findByEmail(email);
            //prepare response dto
            AuthorResponseDto authorResponseDto = new AuthorResponseDto();
            authorResponseDto.setName(author.getName());
            authorResponseDto.setAge(author.getAge());
            return authorResponseDto;
        }
        catch (Exception e){
            throw new AuthorNotFoundException("Wrong email ID");
        }
    }
}
