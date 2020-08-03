package com.example.springUsers.Exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.example.springUsers.Model.Response.CustomExceptionResponse;

import java.util.Date;

@ControllerAdvice
public class CustomAppExceptionHandler {

    @ExceptionHandler(value = {CustomAppException.class})
    public ResponseEntity<Object> handleCustomAppException(CustomAppException exception, WebRequest request) {

        CustomExceptionResponse exceptionResponse = new CustomExceptionResponse(new Date(), exception.getMessage());

        return new ResponseEntity<>(exceptionResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleOtherException(CustomAppException exception, WebRequest request) {

        CustomExceptionResponse exceptionResponse = new CustomExceptionResponse(new Date(), exception.getMessage());

        return new ResponseEntity<>(exceptionResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
