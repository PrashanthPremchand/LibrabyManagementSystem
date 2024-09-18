package com.example.LibraryManagementSystem.expections;

public class CardExpiredException extends Exception{
    public CardExpiredException(String message){
        super(message);
    }
}
