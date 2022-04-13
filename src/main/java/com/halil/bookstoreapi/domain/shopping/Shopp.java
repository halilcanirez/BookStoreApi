package com.halil.bookstoreapi.domain.shopping;

import com.halil.bookstoreapi.adapter.common.DeliveryStatus;
import com.halil.bookstoreapi.adapter.common.OrderList;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class Shopp {
    private Long customerId;
    private List<OrderList> orderLists;
    private DeliveryStatus deliveryStatus;
    private BigDecimal totalPrice;
}
