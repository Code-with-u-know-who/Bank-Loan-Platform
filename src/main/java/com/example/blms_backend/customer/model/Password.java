package com.example.blms_backend.customer.model;

import jakarta.persistence.*;

@Entity
public class Password {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String passwordChars;

    @OneToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

    public Integer getId() {
        return id;
    }

    public String getPasswordChars() {
        return passwordChars;
    }

    public void setPasswordChars(String passwordChars) {
        this.passwordChars = passwordChars;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
