package com.halil.bookstoreapi.adapter.jpa.customer;

import com.halil.bookstoreapi.domain.customer.Customer;
import com.halil.bookstoreapi.domain.exception.BookStoreApiDataNotFoundException;
import com.halil.bookstoreapi.domain.exception.ExceptionType;
import com.halil.bookstoreapi.domain.port.CustomerPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerJpaAdapter implements CustomerPersistencePort {
    private final CustomerJpaRepository customerJpaRepository;

    @Override
    public Customer createCustomer(Customer newCustomer) {
        CustomerEntity createdCustomer = customerJpaRepository.save(CustomerEntity.convertTOEntity(newCustomer));
        return createdCustomer.convertToCustomer();
    }

    @Override
    public Customer retrieveCustomer(Long id) {
        return customerJpaRepository.findById(id)
                .orElseThrow(() -> new BookStoreApiDataNotFoundException(ExceptionType.CUSTOMER_DATA_NOT_FOUND))
                .convertToCustomer();
    }

    @Override
    public Customer retrieveCustomerByMail(String mail) {
        return customerJpaRepository.findByMail(mail)
                .orElseThrow(() -> new BookStoreApiDataNotFoundException(ExceptionType.CUSTOMER_DATA_NOT_FOUND))
                .convertToCustomer();
    }

    @Override
    public boolean isMailOrPhoneNumberExist(String mail, String phoneNumber) {
        return customerJpaRepository.existsByMailAndPhoneNumber(mail,phoneNumber);
    }
}
