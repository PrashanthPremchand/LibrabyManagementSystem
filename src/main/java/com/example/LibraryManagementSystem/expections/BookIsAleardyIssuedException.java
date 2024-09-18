package com.example.LibraryManagementSystem.expections;

public class BookIsAleardyIssuedException extends Exception{
    public BookIsAleardyIssuedException(String message){
        super(message);
    }
}
