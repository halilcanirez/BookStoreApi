package com.halil.bookstoreapi.domain.sec.service;

import com.halil.bookstoreapi.domain.customer.Customer;
import com.halil.bookstoreapi.domain.exception.BookStoreApiValidationException;
import com.halil.bookstoreapi.domain.exception.ExceptionType;
import com.halil.bookstoreapi.domain.port.CustomerPersistencePort;
import com.halil.bookstoreapi.domain.sec.dto.SecLoginRequestDto;
import com.halil.bookstoreapi.domain.sec.enums.EnumJwtConstant;
import com.halil.bookstoreapi.domain.sec.security.JwtTokenGenerator;
import com.halil.bookstoreapi.domain.sec.security.JwtUserDetails;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final CustomerPersistencePort customerPersistencePort;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenGenerator jwtTokenGenerator;


    public String login(SecLoginRequestDto secLoginRequestDto) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(secLoginRequestDto.getMail(), secLoginRequestDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenGenerator.generateJwtToken(authentication);
        String bearer = EnumJwtConstant.BEARER.getConstant();
        return bearer + token;
    }

    public Customer getCurrentCustomer() {

        JwtUserDetails jwtUserDetails = getCurrentJwtUserDetails();
        Customer customer = null;
        if (jwtUserDetails != null){
            customer = customerPersistencePort.retrieveCustomer(jwtUserDetails.getId());
        }
        return customer;
    }

    public Long getCurrentCustomerId(){
        JwtUserDetails jwtUserDetails = getCurrentJwtUserDetails();
        Long jwtUserDetailsId = null;
        if (jwtUserDetails != null){
            jwtUserDetailsId = jwtUserDetails.getId();
        }
        return jwtUserDetailsId;
    }

    private JwtUserDetails getCurrentJwtUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JwtUserDetails jwtUserDetails = null;
        if (authentication != null && authentication.getPrincipal() instanceof JwtUserDetails){
            jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
        }
        return jwtUserDetails;
    }
}
