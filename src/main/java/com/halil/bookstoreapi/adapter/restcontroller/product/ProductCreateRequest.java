package com.halil.bookstoreapi.adapter.restcontroller.product;

import com.halil.bookstoreapi.adapter.jpa.common.Category;
import com.halil.bookstoreapi.adapter.jpa.common.ProductStatus;
import com.halil.bookstoreapi.domain.product.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class ProductCreateRequest {
    private BigDecimal price;
    private String title;
    private ProductStatus status;
    private LocalDateTime createdDate;
    private Category bookCategory;
    private Integer stock;

    public Product convertToProduct(){
        return Product.builder()
                .price(price)
                .title(title)
                .status(status)
                .createdDate(createdDate)
                .bookCategory(bookCategory)
                .stock(stock)
                .build();
    }
}
