package com.halil.bookstoreapi.adapter.jpa.product;

import com.halil.bookstoreapi.adapter.jpa.common.Category;
import com.halil.bookstoreapi.adapter.jpa.common.ProductStatus;
import com.halil.bookstoreapi.domain.product.Product;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;

@Table(name = "products")
@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductStatus status;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdDate;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category bookCategory;

    @Column(nullable = false)
    @Min(value = 0)
    private Integer stock;

    public static ProductEntity convertToProductEntity(Product product){
        ProductEntity productEntity = new ProductEntity();
        productEntity.setPrice(product.getPrice());
        productEntity.setTitle(product.getTitle());
        productEntity.setStatus(product.getStatus());
        productEntity.setCreatedDate(product.getCreatedDate());
        productEntity.setBookCategory(product.getBookCategory());
        productEntity.setStock(product.getStock());
        return productEntity;
    }

    public Product convertToProduct(){
        return Product.builder()
                .id(id)
                .price(price)
                .title(title)
                .status(status)
                .createdDate(createdDate)
                .bookCategory(bookCategory)
                .stock(stock)
                .build();
    }
}
