package com.halil.bookstoreapi.adapter.scheduledoperations;

import com.halil.bookstoreapi.adapter.jpa.product.ProductEntity;
import com.halil.bookstoreapi.adapter.jpa.product.ProductJpaRepository;
import com.halil.bookstoreapi.adapter.redis.RedisCrudRepository;
import com.halil.bookstoreapi.adapter.redis.StockEvent;
import com.halil.bookstoreapi.domain.port.ProductPersistencePort;
import com.halil.bookstoreapi.domain.port.StockEventPort;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class Tasks {
    private final RedisCrudRepository redisCrudRepository;
    private final ProductJpaRepository productJpaRepository;
    @Scheduled(cron = "0 03 * * * ?", zone = "Europe/Paris")
    public void updateStockWithEventBusAndCleanEventBus(){
        Map<Long,Integer> updateList= new HashMap<>();

        Set<Long> productIdList = redisCrudRepository.findAll().stream()
                .map(stockEvent -> stockEvent.getBookId())
                .collect(Collectors.toSet());

        productIdList.forEach(c -> {
            List<StockEvent> events = redisCrudRepository.findAllByBookId(c);
            Integer stocks = events.stream().map(event -> event.getQuantity()).collect(Collectors.toList()) // TODO
                    .stream().collect(Collectors.summingInt(Integer::intValue));
            updateList.put(c,stocks);
        });

        updateList.entrySet().stream()
                .forEach(e -> {
                    ProductEntity productEntity = productJpaRepository.findById(e.getKey()).get();
                    productEntity.setStock(productEntity.getStock()-e.getValue());
                    productJpaRepository.save(productEntity);
                });
        //TODO clean event bus
    }
}
