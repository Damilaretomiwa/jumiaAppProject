package org.example.controllers;

import lombok.AllArgsConstructor;
import org.example.data.models.Product;
import org.example.data.repositories.ProductRepository;
import org.example.services.ProductServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@RequestMapping("api/v1/Product")
@RestController
public class ProductController {

    private ProductServices productServices;
    private ProductRepository productRepository;

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productServices.save(product);
        return ResponseEntity.ok(createdProduct);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable UUID productId) {
        Optional<Product> product = productRepository.findById(productId);
        return product.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());


    }
}