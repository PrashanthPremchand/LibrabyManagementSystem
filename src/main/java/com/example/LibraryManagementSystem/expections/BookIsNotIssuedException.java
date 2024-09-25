package com.example.LibraryManagementSystem.expections;

public class BookIsNotIssuedException extends Exception{
    public BookIsNotIssuedException(String message){
        super(message);
    }
}
