package com.halil.bookstoreapi.domain.shopping;

import com.halil.bookstoreapi.adapter.common.OrderList;
import com.halil.bookstoreapi.domain.exception.BookStoreApiValidationException;
import com.halil.bookstoreapi.domain.port.ShoppingPersistencePort;
import com.halil.bookstoreapi.domain.port.StockEventPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShoppingServiceTest {
    ShoppingService shoppingService;

    @Mock
    StockEventPort stockEventPort;
    @Mock
    ShoppingPersistencePort shoppingPersistencePort;
    @BeforeEach
    void setup(){
        shoppingService = new ShoppingService(
                stockEventPort,shoppingPersistencePort
        );
    }
    @Test
    void should_not_create_shopp_when_item_list_empty(){
        //given
        List<OrderList> itemList = Collections.<OrderList>emptyList();
        Shopp shopp=new Shopp();
        shopp.setCustomerId(1L);
        shopp.setOrderLists(itemList);

        // when
        Throwable throwable = catchThrowable(() -> shoppingService.createOrder(shopp));
        //then
        assertThat(throwable)
                .isNotNull()
                .isInstanceOf(BookStoreApiValidationException.class);
    }

    @Test
    void should_create_shopp(){
        //given
        OrderList orderList=new OrderList();
        orderList.setProductId(1L);
        orderList.setQuantity(5);
        List<OrderList> itemList = List.of(orderList);
        Shopp shopp=new Shopp();
        shopp.setCustomerId(1L);
        shopp.setOrderLists(itemList);
        //mock
        Long mockId = 1L;
        when(shoppingPersistencePort.createShopp(shopp)).thenReturn(mockId);
        doNothing().when(stockEventPort).addStockEvent(any(),any());

        // when
        Long shoppID = shoppingService.createOrder(shopp);
        //then
        assertThat(shoppID).isNotNull();
        assertThat(shoppID).isEqualTo(mockId);
    }

}