package com.example.springUsers.Exceptions;

import org.springframework.http.HttpStatus;

import java.util.Date;

public class  CustomAppException extends Exception {

    private String message;

    public CustomAppException(String message) {
        super(message);
    }
}