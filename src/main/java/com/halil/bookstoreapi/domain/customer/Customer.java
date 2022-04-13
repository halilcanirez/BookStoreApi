package com.halil.bookstoreapi.domain.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Customer{
    private Long id;
    private String name;
    private String surname;
    private String mail;
    private String password;
    private String phoneNumber;
    public void encodePassword(String password) {
        this.password = password;
    }
}
