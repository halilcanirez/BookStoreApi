package com.halil.bookstoreapi.adapter.restcontroller.product;

import com.halil.bookstoreapi.adapter.jpa.common.Category;
import com.halil.bookstoreapi.adapter.jpa.common.ProductStatus;
import com.halil.bookstoreapi.domain.product.Product;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class ProductResponse {
    private Long id;
    private BigDecimal price;
    private String title;
    private ProductStatus status;
    private LocalDateTime createdDate;
    private Category bookCategory;
    private Integer stock;

    public static ProductResponse convertToProduct(Product product){
        return ProductResponse.builder()
                .id(product.getId())
                .price(product.getPrice())
                .title(product.getTitle())
                .status(product.getStatus())
                .createdDate(product.getCreatedDate())
                .bookCategory(product.getBookCategory())
                .stock(product.getStock())
                .build();
    }
}
