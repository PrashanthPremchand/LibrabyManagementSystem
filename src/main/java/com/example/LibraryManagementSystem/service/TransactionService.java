package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.dto.requestdto.IssueBookRequestDto;
import com.example.LibraryManagementSystem.dto.responsedto.IssueBookResponseDto;
import com.example.LibraryManagementSystem.expections.BookIsAleardyIssuedException;
import com.example.LibraryManagementSystem.expections.BookNotFoundException;
import com.example.LibraryManagementSystem.expections.CardExpiredException;
import com.example.LibraryManagementSystem.expections.CardNotFoundException;

public interface TransactionService {
    public IssueBookResponseDto issueBook(IssueBookRequestDto issueBookRequestDto) throws BookNotFoundException, CardNotFoundException, BookIsAleardyIssuedException, CardExpiredException;
}
