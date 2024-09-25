package com.example.LibraryManagementSystem.service.impl;

import com.example.LibraryManagementSystem.dto.requestdto.BookRequestDto;
import com.example.LibraryManagementSystem.dto.responsedto.FindAllBookOfGenreResponseDto;
import com.example.LibraryManagementSystem.dto.responsedto.FindAuthorsNameOfAllBooksByGenreResponseDto;
import com.example.LibraryManagementSystem.entity.Author;
import com.example.LibraryManagementSystem.entity.Book;
import com.example.LibraryManagementSystem.enums.Gender;
import com.example.LibraryManagementSystem.enums.Genre;
import com.example.LibraryManagementSystem.expections.BookNotFoundException;
import com.example.LibraryManagementSystem.repository.AuthorRepository;
import com.example.LibraryManagementSystem.repository.BookRepository;
import com.example.LibraryManagementSystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    BookRepository bookRepository;

    @Override
    public String addBook(BookRequestDto bookRequestDto) throws Exception {
        Book book = new Book();
        Author author;
        try{
            author = authorRepository.findById(bookRequestDto.getAuthorId()).get();
            book.setTitle(bookRequestDto.getTitle());
            book.setGenre(bookRequestDto.getGenre());
            book.setNoOfPages(bookRequestDto.getNoOfPages());
            book.setPrice(bookRequestDto.getPrice());
        }
        catch (Exception e){
            throw new Exception("Author not present");
        }

        book.setAuthor(author);
        author.getBooks().add(book);
        authorRepository.save(author);
        return "Book added";
    }

    @Override
    public FindAllBookOfGenreResponseDto getByGenre(Genre genre) {
        List<Book> books = bookRepository.findByGenre(genre);
        //prepare response dto
        FindAllBookOfGenreResponseDto findAllBookOfGenreResponseDto = new FindAllBookOfGenreResponseDto();
        for(Book currentBook : books){
            findAllBookOfGenreResponseDto.getTitles().add(currentBook.getTitle());
        }
        return findAllBookOfGenreResponseDto;
    }

    @Override
    public FindAuthorsNameOfAllBooksByGenreResponseDto getAuthorNamesByGenre(Genre genre) {
        List<Book> books = bookRepository.findByGenre(genre);
        //prepare response dto
        FindAuthorsNameOfAllBooksByGenreResponseDto findAuthorsNameOfAllBooksByGenreResponseDto = new FindAuthorsNameOfAllBooksByGenreResponseDto();
        for (Book currentBook : books){
            findAuthorsNameOfAllBooksByGenreResponseDto.getNames().add(currentBook.getAuthor().getName());
        }
        return findAuthorsNameOfAllBooksByGenreResponseDto;
    }

    @Override
    public String deleteBook(int id) throws BookNotFoundException {
        try{
            Book book = bookRepository.findById(id).get();
            int authorId = book.getAuthor().getId();
            bookRepository.deleteById(id);
            Author author = authorRepository.findById(authorId).get();
            author.getBooks().remove(book);
            return "Book successfully removed";
        } catch (Exception e){
            throw new BookNotFoundException("Book not found");
        }
    }
}
