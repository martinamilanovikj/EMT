package com.example.emt1.model.exceptions;

public class BookNotAvailableException extends RuntimeException {
    public BookNotAvailableException(){
        super("Not Available");
    }
}