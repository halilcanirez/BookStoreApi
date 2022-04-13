package com.halil.bookstoreapi.adapter.restcontroller.excepitonhandler;

import com.halil.bookstoreapi.domain.exception.BookStoreApiDataNotFoundException;
import com.halil.bookstoreapi.domain.exception.BookStoreApiValidationException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestControllerAdvice
public class BookStoreApiRestExceptionHandler {

    @ExceptionHandler(BookStoreApiDataNotFoundException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)  // isteği işleyemedim
    public ExceptionResponse handleDataNotFoundException(BookStoreApiDataNotFoundException exception){
        return new ExceptionResponse(exception.getExceptionType());
    }

    @ExceptionHandler(BookStoreApiValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleValidationException(BookStoreApiValidationException exception){
        return new ExceptionResponse(exception.getExceptionType());
    }
}
