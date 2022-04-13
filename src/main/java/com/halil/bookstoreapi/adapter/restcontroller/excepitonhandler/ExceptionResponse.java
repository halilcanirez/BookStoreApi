package com.halil.bookstoreapi.adapter.restcontroller.excepitonhandler;

import com.halil.bookstoreapi.domain.exception.ExceptionType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor // testler için
public class ExceptionResponse {
    private Integer code;
    private String message;

    public ExceptionResponse(ExceptionType exceptionType){
        this.code=exceptionType.getCode();
        this.message=exceptionType.getMessage();
    }
}
