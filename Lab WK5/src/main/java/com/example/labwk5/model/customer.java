package com.example.labwk5.model;

import jakarta.validation.constraints.*;

public class customer {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Email(message = "Email must be valid")
    private String email;

    @Min(value = 18, message = "Age must be at least 18")
    private int age;

    @NotBlank(message = "Address cannot be blank")
    private String address;

    public customer() {}

    public customer(String name, String email, int age, String address) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.address = address;
    }

    // Getters / Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
}