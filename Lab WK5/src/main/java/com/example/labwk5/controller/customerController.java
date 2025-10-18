package com.example.labwk5.controller;

import com.example.labwk5.model.customer;
import com.example.labwk5.service.customerService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class customerController {
    private final customerService customerService;

    public customerController(customerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<String> addCustomer(@Valid @RequestBody customer c) {
        customerService.addCustomer(c);
        return ResponseEntity.status(HttpStatus.CREATED).body("Customer added successfully");
    }

    @GetMapping
    public List<customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{email}")
    public customer getCustomer(@PathVariable String email) {
        return customerService.getCustomerByEmail(email);
    }

    @PutMapping("/{email}")
    public ResponseEntity<String> updateCustomer(@PathVariable String email, @Valid @RequestBody customer updated) {
        customerService.updateCustomer(email, updated);
        return ResponseEntity.ok("Customer updated successfully");
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<String> deleteCustomer(@PathVariable String email) {
        customerService.deleteCustomer(email);
        return ResponseEntity.ok("Customer deleted successfully");
    }
}