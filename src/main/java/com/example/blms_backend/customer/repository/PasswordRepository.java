package com.example.blms_backend.customer.repository;

import com.example.blms_backend.customer.model.Password;
import org.springframework.data.repository.CrudRepository;

public interface PasswordRepository extends CrudRepository<Password, Integer> {
    public Password findByPasswordChars(String password);
}
