package com.halil.bookstoreapi.adapter.restcontroller.shopping;

import com.halil.bookstoreapi.domain.shopping.Shopp;
import com.halil.bookstoreapi.domain.shopping.ShoppingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {
    private final ShoppingService shoppingService;

    @PostMapping
    @ResponseStatus( HttpStatus.CREATED)
    public Long createOrder(@RequestBody OrderCreateRequest orderCreateRequest){
        Long orderId = shoppingService.createOrder(orderCreateRequest.convertToOrder());
        return orderId;
    }

    @GetMapping("/customer/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponse> retrieveAllByCustomerId(@PathVariable Long customerId){
        List<Shopp> list = shoppingService.retrieveAllByCustomerId(customerId);
        return list.stream().map(OrderResponse::convertToOrderResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{shoppId}")
    @ResponseStatus(HttpStatus.OK)
    public OrderResponse retrieveShoppDetails(@PathVariable Long shoppId){
        Shopp shopp = shoppingService.retrieveShoppDetails(shoppId);
        return OrderResponse.convertToOrderResponse(shopp);
    }
}
