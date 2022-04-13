package com.halil.bookstoreapi.domain.sec.dto;

import lombok.Data;


@Data
public class SecLoginRequestDto {

    private String mail;
    private String password;
}
