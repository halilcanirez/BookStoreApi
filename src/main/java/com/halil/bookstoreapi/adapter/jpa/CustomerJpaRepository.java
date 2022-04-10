package com.halil.bookstoreapi.adapter.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerJpaRepository extends JpaRepository<CustomerEntity,Long> {
    Optional<CustomerEntity> findByMail(String mail);
    boolean existsByMailAndPhoneNumber(String mail,String phoneNumber);
}
