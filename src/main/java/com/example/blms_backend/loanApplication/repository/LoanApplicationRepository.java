package com.example.blms_backend.loanApplication.repository;

import com.example.blms_backend.loanApplication.model.LoanApplication;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LoanApplicationRepository extends CrudRepository<LoanApplication, Integer> {
//    public List<LoanApplication> getAllLoanAppByCustomerId(Integer id);

//    public List<LoanApplication> getAllLoanAppByLoanProductName(String name);

}
