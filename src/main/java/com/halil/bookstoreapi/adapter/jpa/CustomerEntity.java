package com.halil.bookstoreapi.adapter.jpa;

import com.halil.bookstoreapi.domain.customer.Customer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Table
@Entity
public class CustomerEntity {

    @Id
    @SequenceGenerator(name = "Customer" , sequenceName = "CUSTOMER_ID_SEQ")
    @GeneratedValue(generator = "Customer")
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private String surname;

    @Column(nullable = false)
    private String mail;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phoneNumber;

    public static CustomerEntity convertTOEntity(Customer customer){
        CustomerEntity customerEntity=new CustomerEntity();
        customerEntity.setMail(customer.getMail());
        customerEntity.setName(customer.getName());
        customerEntity.setSurname(customer.getSurname());
        customerEntity.setPassword(customer.getPassword());
        customerEntity.setPhoneNumber(customer.getPhoneNumber());
        return customerEntity;
    }
    public Customer convertToCustomer(){
        return Customer.builder()
                    .id(id)
                    .name(name)
                    .surname(surname)
                    .mail(mail)
                    .password(password)
                    .phoneNumber(phoneNumber)
                    .build();
    }
}
