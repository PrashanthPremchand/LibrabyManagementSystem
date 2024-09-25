package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.dto.requestdto.IssueBookRequestDto;
import com.example.LibraryManagementSystem.dto.requestdto.ReturnBookRequestDto;
import com.example.LibraryManagementSystem.dto.responsedto.IssueBookResponseDto;
import com.example.LibraryManagementSystem.dto.responsedto.ReturnBookResponseDto;
import com.example.LibraryManagementSystem.expections.*;

public interface TransactionService {
    public IssueBookResponseDto issueBook(IssueBookRequestDto issueBookRequestDto) throws BookNotFoundException, CardNotFoundException, BookIsAleardyIssuedException, CardExpiredException;
    public ReturnBookResponseDto returnBook(ReturnBookRequestDto returnBookRequestDto) throws BookNotFoundException, CardNotFoundException, BookIsNotIssuedException, CardExpiredException;
}
