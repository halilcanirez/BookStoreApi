package com.halil.bookstoreapi.domain.exception;

import lombok.Getter;

@Getter
public class BookStoreApiDataNotFoundException extends RuntimeException{
    private final ExceptionType exceptionType;

    public BookStoreApiDataNotFoundException( ExceptionType exceptionType) {
        super(exceptionType.getMessage());
        this.exceptionType = exceptionType;
    }
}
