package com.halil.bookstoreapi.adapter.redis;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("StockEvent")
public class StockEvent implements Serializable {
    private String id;
    private Long bookId;
    private Integer quantity;
}
