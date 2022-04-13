package com.halil.bookstoreapi.adapter.restcontroller.customer;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginRequest {

    @NotBlank(message = "mail is not null")
    String mail;

    @NotBlank(message = "password is not null")
    private String password;
}
