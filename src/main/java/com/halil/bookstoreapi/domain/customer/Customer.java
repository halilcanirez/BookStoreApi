package com.halil.bookstoreapi.domain.customer;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Customer{
    private Long id;
    private String name;
    private String surname;
    private String mail;
    private String password;
    private String phoneNumber;
}
