package com.example.blms_backend.loanProduct.repository;

import com.example.blms_backend.loanProduct.model.LoanProduct;
import org.springframework.data.repository.CrudRepository;

public interface LoanProductRepository extends CrudRepository<LoanProduct, Integer> {
}
