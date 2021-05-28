package com.spring.api.controllers;

import com.spring.api.ProductNotFoundException;
import com.spring.api.models.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    private List<Product> getProductsList() {

        return Arrays.asList(

                new Product(10, "mobile", "latest mobile"),
                new Product(11, "laptop", "new laptaps arrived"),
                new Product(12, "chair", "ikea chairs"),
                new Product(13, "bed", "kurlon beds best for good sleep"),
                new Product(14, "book", "read as many as books")
        );

    }

    @GetMapping("/product")
    public ResponseEntity<Product> getProducts(@RequestParam(name = "id") Integer productId) {

        final List<Product> productsList = getProductsList();
        Product productVo = productsList.stream().
                filter(product -> productId.equals(product.getProductId()))
                .findAny().orElseThrow(() -> new ProductNotFoundException("Product not found"));

        return new ResponseEntity<>(productVo, HttpStatus.OK);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleException(ProductNotFoundException exception) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
}
