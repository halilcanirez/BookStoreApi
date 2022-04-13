package com.halil.bookstoreapi.domain.exception;

import lombok.Getter;

@Getter
public class BookStoreApiValidationException extends RuntimeException{
    private final ExceptionType exceptionType;

    public BookStoreApiValidationException( ExceptionType exceptionType) {
        super(exceptionType.getMessage());
        this.exceptionType = exceptionType;
    }
}
