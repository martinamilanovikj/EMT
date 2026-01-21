package com.example.emtlabs.model.exceptions;

public class BookNotAvailableException extends RuntimeException {
    public BookNotAvailableException(){
        super("Not Available");
    }
}