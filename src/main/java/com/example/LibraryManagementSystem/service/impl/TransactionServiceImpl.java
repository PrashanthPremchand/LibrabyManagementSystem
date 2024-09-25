package com.example.LibraryManagementSystem.service.impl;

import com.example.LibraryManagementSystem.dto.requestdto.IssueBookRequestDto;
import com.example.LibraryManagementSystem.dto.requestdto.ReturnBookRequestDto;
import com.example.LibraryManagementSystem.dto.responsedto.IssueBookResponseDto;
import com.example.LibraryManagementSystem.dto.responsedto.ReturnBookResponseDto;
import com.example.LibraryManagementSystem.entity.Book;
import com.example.LibraryManagementSystem.entity.Card;
import com.example.LibraryManagementSystem.entity.Transaction;
import com.example.LibraryManagementSystem.enums.CardStatus;
import com.example.LibraryManagementSystem.enums.TransactionStatus;
import com.example.LibraryManagementSystem.expections.*;
import com.example.LibraryManagementSystem.repository.BookRepository;
import com.example.LibraryManagementSystem.repository.CardRepository;
import com.example.LibraryManagementSystem.repository.TransactionRepository;
import com.example.LibraryManagementSystem.service.TransactionService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    private JavaMailSender emailSender;

    @Override
    public IssueBookResponseDto issueBook(IssueBookRequestDto issueBookRequestDto) throws BookNotFoundException, CardNotFoundException, BookIsAleardyIssuedException, CardExpiredException {
        Transaction transaction = new Transaction();
        transaction.setTransactionNumber(String.valueOf(UUID.randomUUID()));
        transaction.setIssue(true);

        Book book;
        try{
            book = bookRepository.findById(issueBookRequestDto.getBookId()).get();
        }
        catch (Exception e){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new BookNotFoundException("Book ID is invalid");
        }
        transaction.setBook(book);

        Card card;
        try{
            card = cardRepository.findById(issueBookRequestDto.getCardId()).get();
        }
        catch (Exception e){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new CardNotFoundException("Card ID is invalid");
        }
        transaction.setCard(card);

        if(book.isIssued()){
            //the book is already issued
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new BookIsAleardyIssuedException("Book is already issued");
        }
        if(card.getCardStatus() != CardStatus.ACTIVATED){
            //the card is expired
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new CardExpiredException("Card is expired");
        }

        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        book.setIssued(true);
        book.setCard(card);
        book.getTransactionList().add(transaction);
        card.getBooks().add(book);
        card.getTransactionList().add(transaction);

        cardRepository.save(card);//since card is the parent of book and transaction saving card will save all card, book and transaction repository

        //prepare response dto
        IssueBookResponseDto issueBookResponseDto = new IssueBookResponseDto();
        issueBookResponseDto.setTransactionNumber(transaction.getTransactionNumber());
        issueBookResponseDto.setTransactionStatus(transaction.getTransactionStatus());
        issueBookResponseDto.setBookName(book.getTitle());

        //Email sending
//        String text = "Congratulations your have been issued a book though LMS project" + book.getTitle();
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("noreply@baeldung.com");
//        message.setTo(card.getStudent().getEmail());
//        message.setSubject("Issued book through LMS project");
//        message.setText(text);
//        emailSender.send(message);

        return issueBookResponseDto;
    }

    @Override
    public ReturnBookResponseDto returnBook(ReturnBookRequestDto returnBookRequestDto) throws BookNotFoundException, CardNotFoundException, BookIsNotIssuedException, CardExpiredException {
        Transaction transaction = new Transaction();
        transaction.setTransactionNumber(String.valueOf(UUID.randomUUID()));
        transaction.setIssue(false);

        Book book;
        try{
            book = bookRepository.findById(returnBookRequestDto.getBookId()).get();
        } catch (Exception e){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new BookNotFoundException("Book ID is invalid");
        }
        transaction.setBook(book);

        Card card;
        try{
            card = cardRepository.findById(returnBookRequestDto.getCardId()).get();
        } catch(Exception e){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new CardNotFoundException("Card ID is invalid");
        }
        transaction.setCard(card);

        if(!book.isIssued()){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new BookIsNotIssuedException("Book is not issued");
        }
        if(card.getCardStatus() != CardStatus.ACTIVATED){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new CardExpiredException("Card is expired");
        }

        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        book.setIssued(false);
        book.setCard(null);
        book.getTransactionList().add(transaction);
        card.getBooks().remove(book);
        card.getTransactionList().add(transaction);

        cardRepository.save(card);//since card is the parent of book and transaction saving card will save all card, book and transaction repository

        //prepare response dto
        ReturnBookResponseDto returnBookResponseDto = new ReturnBookResponseDto();
        returnBookResponseDto.setTransactionNumber(transaction.getTransactionNumber());
        returnBookResponseDto.setTransactionStatus(transaction.getTransactionStatus());
        returnBookResponseDto.setBookName(book.getTitle());

        //Email sending
//        String text = "Congratulations your have successfully returned the book though LMS project" + book.getTitle();
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("noreply@baeldung.com");
//        message.setTo(card.getStudent().getEmail());
//        message.setSubject("Returned book through LMS project");
//        message.setText(text);
//        emailSender.send(message);

        return returnBookResponseDto;
    }
}
