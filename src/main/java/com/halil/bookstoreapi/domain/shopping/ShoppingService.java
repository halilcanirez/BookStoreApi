package com.halil.bookstoreapi.domain.shopping;

import com.halil.bookstoreapi.domain.exception.BookStoreApiValidationException;
import com.halil.bookstoreapi.domain.exception.ExceptionType;
import com.halil.bookstoreapi.domain.port.ShoppingPersistencePort;
import com.halil.bookstoreapi.domain.port.StockEventPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShoppingService {
    private final StockEventPort stockEventPort;
    private final ShoppingPersistencePort shoppingPersistencePort;

    public Long createOrder(Shopp newShopp){
        if(Boolean.TRUE.equals(newShopp.getOrderLists().isEmpty())){
            throw new BookStoreApiValidationException(ExceptionType.SHOPP_ITEMS_EMPTY);
        }
        Long shoppId = shoppingPersistencePort.createShopp(newShopp);
        newShopp.getOrderLists().stream()
                        .forEach(orderList -> {
                            stockEventPort.addStockEvent(orderList.getProductId(),-1*orderList.getQuantity());
                        });
        return shoppId;
    }
    public List<Shopp> retrieveAllByCustomerId(Long customerId){
        return shoppingPersistencePort.ordersOfCustomers(customerId);
    }

    public Shopp retrieveShoppDetails(Long id){
        return shoppingPersistencePort.retrieveShoppDetails(id);
    }
}
