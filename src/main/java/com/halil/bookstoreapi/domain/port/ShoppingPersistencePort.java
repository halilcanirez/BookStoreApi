package com.halil.bookstoreapi.domain.port;

import com.halil.bookstoreapi.domain.shopping.Shopp;

import java.util.List;

public interface ShoppingPersistencePort{
    Long createShopp(Shopp newShopp);
    List<Shopp> ordersOfCustomers(Long customerId);
    Shopp retrieveShoppDetails(Long id);
}
