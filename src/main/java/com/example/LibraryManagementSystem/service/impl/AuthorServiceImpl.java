package com.example.LibraryManagementSystem.service.impl;

import com.example.LibraryManagementSystem.dto.requestdto.AuthorRequestDto;
import com.example.LibraryManagementSystem.dto.requestdto.UpdateAuthorAgeRequestDto;
import com.example.LibraryManagementSystem.dto.requestdto.UpdateAuthorEmailRequestDTO;
import com.example.LibraryManagementSystem.dto.requestdto.UpdateAuthorNameRequestDto;
import com.example.LibraryManagementSystem.dto.responsedto.*;
import com.example.LibraryManagementSystem.entity.Author;
import com.example.LibraryManagementSystem.entity.Book;
import com.example.LibraryManagementSystem.expections.AuthorNotFoundException;
import com.example.LibraryManagementSystem.repository.AuthorRepository;
import com.example.LibraryManagementSystem.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    @Override
    public String addAuthor(AuthorRequestDto authorRequestDto) {
        Author author = new Author();
        author.setName(authorRequestDto.getName());
        author.setAge(authorRequestDto.getAge());
        author.setEmail(authorRequestDto.getEmail());
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

    @Override
    public BooksOfAuthorResponseDto getBooksOfAuthor(int id) throws AuthorNotFoundException {
        try{
            Author author = authorRepository.findById(id).get();
            //prepare response dto
            BooksOfAuthorResponseDto booksOfAuthorResponseDto = new BooksOfAuthorResponseDto();
            List<Book> books = author.getBooks();
            if (books != null) {
                for (Book currentBook : books) {
                    if (currentBook != null) {
                        booksOfAuthorResponseDto.getBooks().add(currentBook.getTitle());
                    }
                }
            }

            return booksOfAuthorResponseDto;
        } catch (Exception e){
            throw new AuthorNotFoundException("Invalid author ID");
        }
    }

    @Override
    public FindAuthorWithMoreThanNumberOfBooks getAuthorsWithMoreThanNumberOfBooks(int numberOfBooks) {
        List<Author> authors = authorRepository.findAll();
        FindAuthorWithMoreThanNumberOfBooks findAuthorWithMoreThanNumberOfBooks = new FindAuthorWithMoreThanNumberOfBooks();
        for(Author author : authors){
            if(author.getBooks().size() > numberOfBooks) findAuthorWithMoreThanNumberOfBooks.getNames().add(author.getName());
        }
        return findAuthorWithMoreThanNumberOfBooks;
    }

    @Override
    public UpdateAuthorNameResponseDto updateName(UpdateAuthorNameRequestDto updateAuthorNameRequestDto) throws AuthorNotFoundException {
        try{
            Author author = authorRepository.findById(updateAuthorNameRequestDto.getId()).get();
            author.setName(updateAuthorNameRequestDto.getName());
            Author updatedAuthor = authorRepository.save(author);
            //prepare response dto
            UpdateAuthorNameResponseDto updateAuthorNameResponseDto = new UpdateAuthorNameResponseDto();
            updateAuthorNameResponseDto.setId(updatedAuthor.getId());
            updateAuthorNameResponseDto.setName(updatedAuthor.getName());

            return updateAuthorNameResponseDto;
        } catch (Exception e){
            throw new AuthorNotFoundException("Invalid author ID");
        }
    }

    @Override
    public UpdateAuthorAgeResponseDto updateAge(UpdateAuthorAgeRequestDto updateAuthorAgeRequestDto) throws AuthorNotFoundException {
        try{
            Author author = authorRepository.findById(updateAuthorAgeRequestDto.getId()).get();
            author.setAge(updateAuthorAgeRequestDto.getAge());
            Author updatedAuthor = authorRepository.save(author);
            //prepare response dto
            UpdateAuthorAgeResponseDto updateAuthorAgeResponseDto = new UpdateAuthorAgeResponseDto();
            updateAuthorAgeResponseDto.setName(updatedAuthor.getName());
            updateAuthorAgeResponseDto.setAge(updatedAuthor.getAge());

            return updateAuthorAgeResponseDto;
        } catch (Exception e){
            throw new AuthorNotFoundException("Invalid author ID");
        }
    }

    @Override
    public UpdateAuthorEmailResponseDto updateEmail(UpdateAuthorEmailRequestDTO updateAuthorEmailRequestDTO) throws AuthorNotFoundException {
        try{
            Author author = authorRepository.findById(updateAuthorEmailRequestDTO.getId()).get();
            author.setEmail(updateAuthorEmailRequestDTO.getEmail());
            Author updatedAuthor = authorRepository.save(author);
            //prepare response dto
            UpdateAuthorEmailResponseDto updateAuthorEmailResponseDto = new UpdateAuthorEmailResponseDto();
            updateAuthorEmailResponseDto.setName(updatedAuthor.getName());
            updateAuthorEmailResponseDto.setEmail(updatedAuthor.getEmail());

            return updateAuthorEmailResponseDto;
        } catch (Exception e){
            throw new AuthorNotFoundException("Invalid author ID");
        }
    }
}
