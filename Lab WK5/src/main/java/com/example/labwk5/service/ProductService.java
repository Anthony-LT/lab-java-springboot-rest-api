package com.example.labwk5.service;

import com.example.labwk5.exception.InvalidPriceRangeException;
import com.example.labwk5.exception.ProductNotFoundException;
import com.example.labwk5.model.product;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final List<product> products = new ArrayList<>();

    public void addProduct(@Valid product product) {
        products.add(product);
    }

    public List<product> getAllProducts() {
        return products;
    }

    public product getProductByName(String name) {
        return products.stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException("Product not found: " + name));
    }

    public void updateProduct(String name, @Valid product updatedProduct) {
        product existing = getProductByName(name);
        existing.setName(updatedProduct.getName());
        existing.setPrice(updatedProduct.getPrice());
        existing.setCategory(updatedProduct.getCategory());
        existing.setQuantity(updatedProduct.getQuantity());
    }

    public void deleteProduct(String name) {
        product product = getProductByName(name);
        products.remove(product);
    }

    public List<product> getProductsByCategory(String category) {
        return products.stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    public List<product> getProductsByPriceRange(double min, double max) {
        if (min > max) {
            throw new InvalidPriceRangeException("Invalid price range: min > max");
        }
        return products.stream()
                .filter(p -> p.getPrice() >= min && p.getPrice() <= max)
                .collect(Collectors.toList());
    }
}