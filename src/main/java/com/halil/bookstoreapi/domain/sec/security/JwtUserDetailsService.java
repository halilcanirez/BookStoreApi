package com.halil.bookstoreapi.domain.sec.security;

import com.halil.bookstoreapi.domain.customer.Customer;
import com.halil.bookstoreapi.domain.port.CustomerPersistencePort;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final CustomerPersistencePort customerPersistencePort;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Customer customer = customerPersistencePort.retrieveCustomerByMail(username);
        return JwtUserDetails.create(customer);
    }

    public UserDetails loadUserByUserId(Long id) {

        Customer cusCustomer = customerPersistencePort.retrieveCustomer(id);

        return JwtUserDetails.create(cusCustomer);
    }
}
