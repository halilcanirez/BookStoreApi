package com.halil.bookstoreapi.adapter.jpa.shoppinglist;

import com.halil.bookstoreapi.adapter.jpa.product.ProductEntity;
import com.halil.bookstoreapi.adapter.jpa.shopping.ShoppingEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table
@Entity
@Getter
@Setter
public class ShoppingItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ProductEntity productEntity;

    @ManyToOne
    private ShoppingEntity shoppingEntity;

    private Integer quantity;
}
