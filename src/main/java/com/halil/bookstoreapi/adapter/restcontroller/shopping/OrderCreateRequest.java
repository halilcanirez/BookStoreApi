package com.halil.bookstoreapi.adapter.restcontroller.shopping;

import com.halil.bookstoreapi.adapter.common.DeliveryStatus;
import com.halil.bookstoreapi.adapter.common.OrderList;
import com.halil.bookstoreapi.domain.shopping.Shopp;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class OrderCreateRequest {
    private Long customerId;
    private List<OrderList> orderLists;
    private BigDecimal totalPrice;
    public Shopp convertToOrder(){
        Shopp shopp = new Shopp();
        shopp.setCustomerId(customerId);
        shopp.setOrderLists(orderLists);
        shopp.setTotalPrice(totalPrice);
        shopp.setDeliveryStatus(DeliveryStatus.RECEIVED);
        return shopp;
    }
}
