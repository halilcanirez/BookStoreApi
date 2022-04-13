package com.halil.bookstoreapi.adapter.restcontroller.shopping;

import com.halil.bookstoreapi.adapter.common.DeliveryStatus;
import com.halil.bookstoreapi.adapter.common.OrderList;
import com.halil.bookstoreapi.domain.shopping.Shopp;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderResponse {
    private Long customerId;
    private List<OrderList> list;
    private BigDecimal totalPrice;
    private DeliveryStatus deliveryStatus;

    public static OrderResponse convertToOrderResponse(Shopp shopp){
        OrderResponse orderResponse= new OrderResponse();
        orderResponse.setCustomerId(shopp.getCustomerId());
        orderResponse.setList(shopp.getOrderLists());
        orderResponse.setTotalPrice(shopp.getTotalPrice());
        orderResponse.setDeliveryStatus(shopp.getDeliveryStatus());
        return orderResponse;
    }
}
