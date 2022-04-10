package com.halil.bookstoreapi.adapter.jpa;

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

}
