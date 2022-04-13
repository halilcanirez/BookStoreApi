package com.halil.bookstoreapi.adapter.jpa.product;

import com.halil.bookstoreapi.domain.exception.BookStoreApiDataNotFoundException;
import com.halil.bookstoreapi.domain.exception.ExceptionType;
import com.halil.bookstoreapi.domain.port.ProductPersistencePort;
import com.halil.bookstoreapi.domain.product.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductJpaAdapter implements ProductPersistencePort {
    private final ProductJpaRepository productJpaRepository;

    @Override
    public Long createProduct(Product product) {
        ProductEntity createdProduct = productJpaRepository.save(ProductEntity.convertToProductEntity(product));
        return createdProduct.getId();
    }

    @Override
    public Long updateProductsStock(Long productId, Integer newStock) {
        ProductEntity product = productJpaRepository.findById(productId)
                .orElseThrow(() -> new BookStoreApiDataNotFoundException(ExceptionType.PRODUCT_DATA_NOT_FOUND));
        product.setStock(newStock);
        productJpaRepository.save(product);
        return product.getId();
    }

    @Override
    public Product retrieveProductDetails(Long productId) {
        return productJpaRepository.findById(productId)
                .orElseThrow(() -> new BookStoreApiDataNotFoundException(ExceptionType.PRODUCT_DATA_NOT_FOUND))
                .convertToProduct();
    }
}
