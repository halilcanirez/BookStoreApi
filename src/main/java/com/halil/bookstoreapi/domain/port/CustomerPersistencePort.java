package com.halil.bookstoreapi.domain.port;

import com.halil.bookstoreapi.domain.customer.Customer;

public interface CustomerPersistencePort {
    Customer createCustomer(Customer newCustomer);
    Customer retrieveCustomer(Long id);
    Customer retrieveCustomerByMail(String mail);
    boolean isMailOrPhoneNumberExist(String mail,String phoneNumber);
}
