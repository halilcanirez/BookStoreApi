package com.halil.bookstoreapi.domain.product;

import com.halil.bookstoreapi.adapter.jpa.common.Category;
import com.halil.bookstoreapi.adapter.jpa.common.ProductStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class Product {
    private Long id;
    private BigDecimal price;
    private String title;
    private ProductStatus status;
    private LocalDateTime createdDate;
    private Category bookCategory;
    private Integer stock;
}
