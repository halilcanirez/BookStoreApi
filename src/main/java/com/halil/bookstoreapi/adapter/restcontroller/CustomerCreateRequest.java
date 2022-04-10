package com.halil.bookstoreapi.adapter.restcontroller;

import com.halil.bookstoreapi.domain.customer.Customer;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CustomerCreateRequest {
    private String name;
    private String surname;
    private String mail;
    private String password;
    private String phoneNumber;

    public static Customer convertToCustomer(CustomerCreateRequest createRequest){
        return Customer.builder()
                .name(createRequest.getName())
                .surname(createRequest.getSurname())
                .mail(createRequest.getMail())
                .password(createRequest.getPassword())
                .phoneNumber(createRequest.getPhoneNumber())
                .build();
    }
}
