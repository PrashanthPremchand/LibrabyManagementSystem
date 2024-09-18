package com.example.LibraryManagementSystem.controller;

import com.example.LibraryManagementSystem.dto.requestdto.IssueBookRequestDto;
import com.example.LibraryManagementSystem.dto.responsedto.IssueBookResponseDto;
import com.example.LibraryManagementSystem.expections.BookIsAleardyIssuedException;
import com.example.LibraryManagementSystem.expections.BookNotFoundException;
import com.example.LibraryManagementSystem.expections.CardExpiredException;
import com.example.LibraryManagementSystem.expections.CardNotFoundException;
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
    @PostMapping("/add_transaction")
    public IssueBookResponseDto issueBook(@RequestBody IssueBookRequestDto issueBookRequestDto) throws BookIsAleardyIssuedException, BookNotFoundException, CardExpiredException, CardNotFoundException {
        return transactionService.issueBook(issueBookRequestDto);
    }
}
