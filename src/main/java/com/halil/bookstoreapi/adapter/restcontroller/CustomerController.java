package com.halil.bookstoreapi.adapter.restcontroller;

import com.halil.bookstoreapi.domain.customer.Customer;
import com.halil.bookstoreapi.domain.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerCreatedResponse createCustomer(@RequestBody CustomerCreateRequest customerCreateRequest){
        Customer createdCustomer = customerService.createCustomer(CustomerCreateRequest.convertToCustomer(customerCreateRequest));
        return CustomerCreatedResponse.convertToCustomer(createdCustomer);
    }
}
