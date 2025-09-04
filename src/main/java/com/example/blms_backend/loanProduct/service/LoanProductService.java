package com.example.blms_backend.loanProduct.service;

import com.example.blms_backend.loanProduct.model.LoanProduct;
import com.example.blms_backend.loanProduct.repository.LoanProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoanProductService {

    @Autowired
    private LoanProductRepository loanProductRepo;

    public LoanProduct saveLoanProduct(LoanProduct loanProduct) {
        return loanProductRepo.save(loanProduct);
    }

    public LoanProduct findLoanProductById(Integer id) {
        Optional<LoanProduct> loanProductOpt = loanProductRepo.findById(id);

        return loanProductOpt.isPresent() ? loanProductOpt.get(): null;

    }

    public LoanProduct deleteLoanProduct(Integer id) {
        Optional<LoanProduct> loanProductOpt = loanProductRepo.findById(id);

        if(loanProductOpt.isPresent()) {
            loanProductRepo.delete(loanProductOpt.get());
        }
        return null;
    }

}
