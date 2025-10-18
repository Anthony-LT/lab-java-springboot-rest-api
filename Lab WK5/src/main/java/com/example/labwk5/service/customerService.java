package com.example.labwk5.service;

import com.example.labwk5.model.customer;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class customerService {
    private final List<customer> customers = new ArrayList<>();

    public void addCustomer(customer c) {
        customers.add(c);
    }

    public List<customer> getAllCustomers() {
        return customers;
    }

    public customer getCustomerByEmail(String email) {
        return customers.stream()
                .filter(c -> c.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Customer not found: " + email));
    }

    public void updateCustomer(String email, @Valid customer updated) {
        customer existing = getCustomerByEmail(email);
        existing.setName(updated.getName());
        existing.setAge(updated.getAge());
        existing.setAddress(updated.getAddress());
    }

    public void deleteCustomer(String email) {
        customers.removeIf(c -> c.getEmail().equalsIgnoreCase(email));
    }
}