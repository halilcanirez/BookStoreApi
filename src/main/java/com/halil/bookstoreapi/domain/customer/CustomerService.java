package com.halil.bookstoreapi.domain.customer;

import com.halil.bookstoreapi.domain.port.CustomerPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerPersistencePort customerPersistencePort;
    public Customer createCustomer(Customer newCustomer){
        validateMailAndPhoneNumber(newCustomer.getMail(), newCustomer.getPhoneNumber());
        return customerPersistencePort.createCustomer(newCustomer);
    }

    private void validateMailAndPhoneNumber(String mail, String phoneNumber){
        if(Boolean.TRUE.equals(customerPersistencePort.isMailOrPhoneNumberExist(mail,phoneNumber))){
            throw new RuntimeException("run time"); // TODO exception
        }
    }
}
