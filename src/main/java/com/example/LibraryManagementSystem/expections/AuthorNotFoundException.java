package com.example.LibraryManagementSystem.expections;

public class AuthorNotFoundException extends Exception{
    public AuthorNotFoundException(String message){
        super(message);
    }
}
