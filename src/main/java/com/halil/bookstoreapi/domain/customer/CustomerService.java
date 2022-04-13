package com.halil.bookstoreapi.domain.customer;

import com.halil.bookstoreapi.domain.exception.BookStoreApiValidationException;
import com.halil.bookstoreapi.domain.exception.ExceptionType;
import com.halil.bookstoreapi.domain.port.CustomerPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerPersistencePort customerPersistencePort;
    private final PasswordEncoder passwordEncoder;

    public Customer createCustomer(Customer newCustomer){
        validateMailAndPhoneNumber(newCustomer.getMail(), newCustomer.getPhoneNumber());
        newCustomer.encodePassword(passwordEncoder.encode(newCustomer.getPassword()));
        return customerPersistencePort.createCustomer(newCustomer);
    }

    private void validateMailAndPhoneNumber(String mail, String phoneNumber){
        if(Boolean.TRUE.equals(customerPersistencePort.isMailOrPhoneNumberExist(mail,phoneNumber))){
            throw new BookStoreApiValidationException(ExceptionType.ALREADY_EXISTS);
        }
    }
}
