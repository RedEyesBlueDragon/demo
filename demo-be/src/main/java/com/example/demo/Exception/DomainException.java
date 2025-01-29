package com.example.demo.Exception;

public class DomainException extends RuntimeException {

    private String message;

    public DomainException(String message) {
        super(message);
    }
}
