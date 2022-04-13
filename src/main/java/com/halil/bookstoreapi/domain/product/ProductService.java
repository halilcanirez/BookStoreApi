package com.halil.bookstoreapi.domain.product;

import com.halil.bookstoreapi.adapter.redis.StockEvent;
import com.halil.bookstoreapi.domain.exception.BookStoreApiValidationException;
import com.halil.bookstoreapi.domain.exception.ExceptionType;
import com.halil.bookstoreapi.domain.port.ProductPersistencePort;
import com.halil.bookstoreapi.domain.port.StockEventPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductPersistencePort productPersistencePort;
    private final StockEventPort stockEventPort;

    public Long createProduct(Product product){
        checkProductPrice(product.getPrice());
        return productPersistencePort.createProduct(product);
    }

    public void updateProductStock(Long productId, Integer newStock){
        stockEventPort.addStockEvent(productId,newStock);
    }

    public Product retrieveProductDetails(Long productId){
        Product product = productPersistencePort.retrieveProductDetails(productId);
        product.setStock(calculateRealProductStock(product));
        return product;
    }

    private void checkProductPrice(BigDecimal productPrice){
        boolean check=false;
        int compare = productPrice.compareTo(new BigDecimal(0));
        if (compare!=1){
            throw new BookStoreApiValidationException(ExceptionType.PRICE_NOT_BE_ZERO_OR_LESS_THE_ZERO);
        }
    }

    private Integer calculateRealProductStock(Product product){
        List<StockEvent> events = stockEventPort.findEventsByProductId(product.getId());
        Integer stocks = events.stream().map(event -> event.getQuantity()).collect(Collectors.toList()) // TODO
                .stream().collect(Collectors.summingInt(Integer::intValue));
        return product.getStock()+stocks;
    }
}
