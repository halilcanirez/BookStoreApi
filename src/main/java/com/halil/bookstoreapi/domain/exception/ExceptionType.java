package com.halil.bookstoreapi.domain.exception;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionType {

    CUSTOMER_DATA_NOT_FOUND(1001, "CUSTOMER NOT FOUND"),
    PRODUCT_DATA_NOT_FOUND(1002, "Product Data Not Found"),

    SHOPP_ITEMS_EMPTY(2001, "ITEMS LIST NOT TO BE EMPTY"),
    PRICE_NOT_BE_ZERO_OR_LESS_THE_ZERO(2003, "Price can not be zero or less than zero"),
    ALREADY_EXISTS(2002, "THİS PHONE NUMBER OR MAIL ALREADY USE ");


    private final Integer code;
    private final String message;
}
