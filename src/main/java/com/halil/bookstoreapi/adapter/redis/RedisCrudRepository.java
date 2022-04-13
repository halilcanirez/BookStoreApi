package com.halil.bookstoreapi.adapter.redis;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RedisCrudRepository extends CrudRepository<StockEvent,String> {
    List<StockEvent> findAllByBookId(Long bookId);

    @Override
    List<StockEvent> findAll();
}
