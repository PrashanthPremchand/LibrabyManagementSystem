package com.example.LibraryManagementSystem.repository;

import com.example.LibraryManagementSystem.entity.Book;
import com.example.LibraryManagementSystem.enums.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByGenre(Genre genre);
}
