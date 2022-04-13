package com.halil.bookstoreapi.adapter.restcontroller.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProductStockRequest {
    private Long productID;
    private Integer newStock;
}
