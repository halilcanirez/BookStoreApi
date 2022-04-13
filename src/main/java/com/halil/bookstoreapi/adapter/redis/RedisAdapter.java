package com.halil.bookstoreapi.adapter.redis;

import com.halil.bookstoreapi.domain.port.StockEventPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RedisAdapter implements StockEventPort {
    private final RedisCrudRepository redisCrudRepository;

    @Override
    public void addStockEvent(Long bookId, Integer quantity){
        StockEvent stockEvent = new StockEvent();
        stockEvent.setId(UUID.randomUUID().toString());
        stockEvent.setBookId(bookId);
        stockEvent.setQuantity(quantity);
        redisCrudRepository.save(stockEvent);
        System.out.println("cacheye eklendi");
    }

    @Override
    public List<StockEvent> findEventsByProductId(Long productId) {
        return redisCrudRepository.findAllByBookId(productId);
    }

    @Override
    public List<StockEvent> findAll() {
        return redisCrudRepository.findAll();
    }

}
