package com.halil.bookstoreapi.adapter.jpa.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductJpaRepository extends JpaRepository<ProductEntity,Long> {

}
