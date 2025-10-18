package com.example.labwk5.controller;

import com.example.labwk5.model.product;
import com.example.labwk5.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private static final String API_KEY = "123456";

    public ProductController(ProductService ProductService) {
        this.productService = ProductService;
    }

    // Vérification de la clé API
    private void validateApiKey(String key) {
        if (key == null || !key.equals(API_KEY)) {
            throw new RuntimeException("Missing or invalid API-Key");
        }
    }

    @PostMapping
    public ResponseEntity<String> addProduct(
            @RequestHeader(value = "API-Key", required = false) String apiKey,
            @Valid @RequestBody product product) {
        validateApiKey(apiKey);
        productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body("Product added successfully");
    }

    @GetMapping
    public List<product> getAllProducts(@RequestHeader(value = "API-Key", required = false) String apiKey) {
        validateApiKey(apiKey);
        return productService.getAllProducts();
    }

    @GetMapping("/{name}")
    public product getProduct(@RequestHeader(value = "API-Key", required = false) String apiKey,
                              @PathVariable String name) {
        validateApiKey(apiKey);
        return productService.getProductByName(name);
    }

    @PutMapping("/{name}")
    public ResponseEntity<String> updateProduct(
            @RequestHeader(value = "API-Key", required = false) String apiKey,
            @PathVariable String name,
            @Valid @RequestBody product updatedProduct) {
        validateApiKey(apiKey);
        productService.updateProduct(name, updatedProduct);
        return ResponseEntity.ok("Product updated successfully");
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<String> deleteProduct(
            @RequestHeader(value = "API-Key", required = false) String apiKey,
            @PathVariable String name) {
        validateApiKey(apiKey);
        productService.deleteProduct(name);
        return ResponseEntity.ok("Product deleted successfully");
    }

    @GetMapping("/category/{category}")
    public List<product> getByCategory(
            @RequestHeader(value = "API-Key", required = false) String apiKey,
            @PathVariable String category) {
        validateApiKey(apiKey);
        return productService.getProductsByCategory(category);
    }

    @GetMapping("/price")
    public List<product> getByPriceRange(
            @RequestHeader(value = "API-Key", required = false) String apiKey,
            @RequestParam double min,
            @RequestParam double max) {
        validateApiKey(apiKey);
        return productService.getProductsByPriceRange(min, max);
    }
}