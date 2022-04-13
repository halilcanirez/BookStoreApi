package com.halil.bookstoreapi.domain.port;

import com.halil.bookstoreapi.adapter.redis.StockEvent;

import java.util.List;

public interface StockEventPort {
    void addStockEvent(Long bookId, Integer quantity);
    List<StockEvent> findEventsByProductId(Long productId);
    List<StockEvent> findAll();
}
