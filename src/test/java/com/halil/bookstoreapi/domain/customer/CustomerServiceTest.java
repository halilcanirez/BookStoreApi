package com.halil.bookstoreapi.domain.customer;

import com.halil.bookstoreapi.domain.exception.BookStoreApiValidationException;
import com.halil.bookstoreapi.domain.port.CustomerPersistencePort;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    CustomerService customerService;

    @Mock
    CustomerPersistencePort customerPersistencePort;
    @Mock
    PasswordEncoder passwordEncoder;
    @BeforeEach
    void setup(){
        customerService=new CustomerService(
                customerPersistencePort,passwordEncoder
        );
    }

    @Test
    void should_not_create_account_when_phone_number_already_use(){
        // mock
        Customer mockCustomer = Customer.builder()
                .id(1L)
                .phoneNumber("534231621")
                .name("test name")
                .surname("test surname")
                .password("123456")
                .mail("test mail")
                .build();

        when(customerPersistencePort.isMailOrPhoneNumberExist(mockCustomer.getMail(), mockCustomer.getPhoneNumber())).thenReturn(Boolean.TRUE);

        //when
        Throwable throwable = catchThrowable(() -> customerService.createCustomer(mockCustomer));

        //then
        assertThat(throwable)
                .isNotNull()
                .isInstanceOf(BookStoreApiValidationException.class);
    }


    @Test
    void should_not_create_account_when_mail_already_use(){
        // mock
        Customer mockCustomer = Customer.builder()
                .id(1L)
                .phoneNumber("534231621")
                .name("test name")
                .surname("test surname")
                .password("123456")
                .mail("test mail")
                .build();

        when(customerPersistencePort.isMailOrPhoneNumberExist(mockCustomer.getMail(), mockCustomer.getPhoneNumber())).thenReturn(Boolean.TRUE);

        //when
        Throwable throwable = catchThrowable(() -> customerService.createCustomer(mockCustomer));

        //then
        assertThat(throwable)
                .isNotNull()
                .isInstanceOf(BookStoreApiValidationException.class)
                .hasMessage("THİS PHONE NUMBER OR MAIL ALREADY USE ");
    }

    @Test
    void should_create_account(){
        //mock
        Customer mockCustomer = Customer.builder()
                    .id(1L)
                    .phoneNumber("534231621")
                    .name("test name")
                    .surname("test surname")
                    .password("123456")
                    .mail("test mail")
                    .build();

        when(customerPersistencePort.isMailOrPhoneNumberExist(mockCustomer.getMail(), mockCustomer.getPhoneNumber())).thenReturn(Boolean.FALSE);
        when(customerPersistencePort.createCustomer(any())).thenReturn(mockCustomer);
        when(passwordEncoder.encode(any())).thenReturn("encoded password");
        //when
        customerService.createCustomer(mockCustomer);
        //then
        verifyNoMoreInteractions(customerPersistencePort);
    }



}