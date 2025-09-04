package com.example.blms_backend.customer.repository;

import com.example.blms_backend.customer.model.Customer;
import com.example.blms_backend.customer.model.KycStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    Optional<Customer> findByEmail(String email);
    Optional<Customer> findByName(String name);

    List<Customer> findByKycStatus(KycStatus kycStatus);

//    List<Customer> findByNameContainingIgnoreCase(String name);

}
