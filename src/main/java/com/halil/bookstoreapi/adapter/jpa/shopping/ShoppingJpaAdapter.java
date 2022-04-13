package com.halil.bookstoreapi.adapter.jpa.shopping;

import com.halil.bookstoreapi.adapter.jpa.customer.CustomerEntity;
import com.halil.bookstoreapi.adapter.jpa.customer.CustomerJpaRepository;
import com.halil.bookstoreapi.adapter.jpa.product.ProductEntity;
import com.halil.bookstoreapi.adapter.jpa.product.ProductJpaRepository;
import com.halil.bookstoreapi.adapter.jpa.shoppinglist.ShoppingItem;
import com.halil.bookstoreapi.adapter.jpa.shoppinglist.ShoppingItemJpaRepository;
import com.halil.bookstoreapi.adapter.common.OrderList;
import com.halil.bookstoreapi.domain.exception.BookStoreApiDataNotFoundException;
import com.halil.bookstoreapi.domain.exception.ExceptionType;
import com.halil.bookstoreapi.domain.shopping.Shopp;
import com.halil.bookstoreapi.domain.port.ShoppingPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShoppingJpaAdapter implements ShoppingPersistencePort {

    private final ShoppingJpaRepository shoppingJpaRepository;
    private final CustomerJpaRepository customerJpaRepository;
    private final ProductJpaRepository productJpaRepository;
    private final ShoppingItemJpaRepository shoppingItemJpaRepository;

    @Override
    @Transactional
    public Long createShopp(Shopp newShopp) {
        CustomerEntity customerEntity = customerJpaRepository.findById(newShopp.getCustomerId())
                .orElseThrow(() -> new BookStoreApiDataNotFoundException(ExceptionType.CUSTOMER_DATA_NOT_FOUND));

        ShoppingEntity shopping=new ShoppingEntity();
        shopping.setCustomer(customerEntity);
        shopping.setDeliveryStatus(newShopp.getDeliveryStatus());
        shopping.setTotalPrice(newShopp.getTotalPrice());
        shopping.setCreatedDate(LocalDateTime.now());
        ShoppingEntity savedEntity = shoppingJpaRepository.save(shopping);
        saveShoppingItemsInDb(newShopp.getOrderLists(),savedEntity);
        return savedEntity.getId();
    }
    @Override
    public List<Shopp> ordersOfCustomers(Long customerId){
        List<ShoppingEntity> allByCustomer_id = shoppingJpaRepository.findAllByCustomer_Id(customerId);
        return allByCustomer_id.stream()
                .map(ShoppingEntity::convertToShop)
                .collect(Collectors.toList());
    }
    @Override
    public Shopp retrieveShoppDetails(Long id){
         return shoppingJpaRepository.findById(id).orElseThrow(() -> new BookStoreApiDataNotFoundException(ExceptionType.PRODUCT_DATA_NOT_FOUND))
                .convertToShop();
    }

    private void saveShoppingItemsInDb(List<OrderList> list, ShoppingEntity shoppingEntity){
        List<ShoppingItem> shoppingItems = list.stream()
                .map(c -> {
                    ShoppingItem shoppingItemsEntity = new ShoppingItem();
                    ProductEntity productEntity = productJpaRepository.findById(c.getProductId())
                            .orElseThrow(() -> new BookStoreApiDataNotFoundException(ExceptionType.PRODUCT_DATA_NOT_FOUND));
                    shoppingItemsEntity.setShoppingEntity(shoppingEntity);
                    shoppingItemsEntity.setProductEntity(productEntity);
                    shoppingItemsEntity.setQuantity(c.getQuantity());
                    return shoppingItemsEntity;
                }).collect(Collectors.toList());
        shoppingItemJpaRepository.saveAll(shoppingItems);
    }
}
