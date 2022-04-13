package com.halil.bookstoreapi.domain.product;

import static org.junit.jupiter.api.Assertions.*;

import com.halil.bookstoreapi.adapter.jpa.common.Category;
import com.halil.bookstoreapi.adapter.jpa.common.ProductStatus;
import com.halil.bookstoreapi.adapter.redis.StockEvent;
import com.halil.bookstoreapi.domain.customer.CustomerService;
import com.halil.bookstoreapi.domain.exception.BookStoreApiValidationException;
import com.halil.bookstoreapi.domain.port.ProductPersistencePort;
import com.halil.bookstoreapi.domain.port.StockEventPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    ProductService productService;
    @Mock
    ProductPersistencePort productPersistencePort;
    @Mock
    StockEventPort stockEventPort;
    @BeforeEach
    void setup(){
        productService = new ProductService(
                productPersistencePort,stockEventPort
        );
    }

    @Test
    void should_create_product(){
        // given
        Product product= Product.builder()
                .createdDate(LocalDateTime.now())
                .bookCategory(Category.ACTION)
                .status(ProductStatus.ACTIVE)
                .title("test title")
                .stock(50)
                .price(new BigDecimal(50))
                .build();

        // mock
        Long mockId = 1L;
        when(productPersistencePort.createProduct(any())).thenReturn(mockId);
        //when
        Long productId = productService.createProduct(product);
        //then
        assertThat(productId).isNotNull()
                .isEqualTo(mockId);
        verifyNoMoreInteractions(productPersistencePort);
    }

    @Test
    void should_not_create_product_when_price_equal_to_zero(){
        // given
        Product product= Product.builder()
                .createdDate(LocalDateTime.now())
                .bookCategory(Category.ACTION)
                .status(ProductStatus.ACTIVE)
                .title("test title")
                .stock(50)
                .price(new BigDecimal(0))
                .build();


        //when
        Throwable throwable = catchThrowable(() -> productService.createProduct(product));
        assertThat(throwable)
                .isNotNull()
                .isInstanceOf(BookStoreApiValidationException.class);
    }
    @Test
    void should_not_create_product_when_price_less_then_zero(){
        // given
        Product product= Product.builder()
                .createdDate(LocalDateTime.now())
                .bookCategory(Category.ACTION)
                .status(ProductStatus.ACTIVE)
                .title("test title")
                .stock(50)
                .price(new BigDecimal(-5))
                .build();

        //when
        Throwable throwable = catchThrowable(() -> productService.createProduct(product));
        //then
        assertThat(throwable)
                .isNotNull()
                .isInstanceOf(BookStoreApiValidationException.class);

    }
    @Test
    void should_update_product_stock(){
        // given
        Long productId = 1L;
        Integer stock = 10;
        //mock
        doNothing().when(stockEventPort).addStockEvent(productId,stock);
        //then
        productService.updateProductStock(productId,stock);
        verifyNoMoreInteractions(stockEventPort);
    }

    @Test
    void should_retrieve_product_details(){
        // given
        Long productId=1L;
        //Mock
        StockEvent stockEvent=new StockEvent();
        stockEvent.setId(UUID.randomUUID().toString());
        stockEvent.setBookId(1L);
        stockEvent.setQuantity(-5);
        List<StockEvent> eventList = List.of(stockEvent);

        Product mockProduct= Product.builder()
                .id(1L)
                .createdDate(LocalDateTime.now())
                .bookCategory(Category.ACTION)
                .status(ProductStatus.ACTIVE)
                .title("test title")
                .stock(50)
                .price(new BigDecimal(50))
                .build();

        when(productPersistencePort.retrieveProductDetails(productId)).thenReturn(mockProduct);
        when(stockEventPort.findEventsByProductId(any())).thenReturn(eventList);

        //when
        Product retrievedProduct = productService.retrieveProductDetails(productId);
        //then
        assertThat(retrievedProduct).isNotNull();
        assertThat(retrievedProduct.getId()).isEqualTo(1L);
        assertThat(retrievedProduct.getStock()).isEqualTo(45);


    }
}