package com.halil.bookstoreapi.adapter.jpa.shopping;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoppingJpaRepository extends JpaRepository<ShoppingEntity,Long> {
    List<ShoppingEntity> findAllByCustomer_Id(Long customerId);
}
