package com.halil.bookstoreapi.domain.port;

import com.halil.bookstoreapi.domain.product.Product;

public interface ProductPersistencePort {

    Long createProduct(Product product);
    Long updateProductsStock(Long productId, Integer newStock);
    Product retrieveProductDetails(Long productId);

}
