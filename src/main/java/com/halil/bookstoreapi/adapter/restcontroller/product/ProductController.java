package com.halil.bookstoreapi.adapter.restcontroller.product;

import com.halil.bookstoreapi.domain.product.Product;
import com.halil.bookstoreapi.domain.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long createProduct(@RequestBody ProductCreateRequest productCreateRequest){
        return productService.createProduct(productCreateRequest.convertToProduct());
    }

    @GetMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse retrieveProduct(@PathVariable Long productId){
        Product retrievedProduct = productService.retrieveProductDetails(productId);
        return ProductResponse.convertToProduct(retrievedProduct);
    }

    @PutMapping("/update-stock")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProductResponse(@RequestBody UpdateProductStockRequest request){
        productService.updateProductStock(request.getProductID(),request.getNewStock());
    }

}
