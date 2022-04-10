package com.halil.bookstoreapi.adapter.restcontroller;

import com.halil.bookstoreapi.domain.customer.Customer;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CustomerCreatedResponse {
    private Long id;
    private String name;
    private String surname;
    private String mail;
    private String phoneNumber;

    public static CustomerCreatedResponse convertToCustomer(Customer customer){
        return CustomerCreatedResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .surname(customer.getSurname())
                .mail(customer.getMail())
                .phoneNumber(customer.getPhoneNumber())
                .build();
    }
}
