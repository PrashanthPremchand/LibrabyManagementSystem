package com.example.LibraryManagementSystem.controller;

import com.example.LibraryManagementSystem.dto.requestdto.IssueBookRequestDto;
import com.example.LibraryManagementSystem.dto.requestdto.ReturnBookRequestDto;
import com.example.LibraryManagementSystem.dto.responsedto.IssueBookResponseDto;
import com.example.LibraryManagementSystem.dto.responsedto.ReturnBookResponseDto;
import com.example.LibraryManagementSystem.expections.*;
import com.example.LibraryManagementSystem.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @PostMapping("/issue_book")
    public IssueBookResponseDto issueBook(@RequestBody IssueBookRequestDto issueBookRequestDto) throws BookIsAleardyIssuedException, BookNotFoundException, CardExpiredException, CardNotFoundException {
        return transactionService.issueBook(issueBookRequestDto);
    }
    @PostMapping("/return_book")
    public ReturnBookResponseDto returnBook(@RequestBody ReturnBookRequestDto returnBookRequestDto) throws BookIsNotIssuedException, BookNotFoundException, CardExpiredException, CardNotFoundException {
        return transactionService.returnBook(returnBookRequestDto);
    }
}
