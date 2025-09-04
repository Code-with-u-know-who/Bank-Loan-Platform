package com.example.blms_backend.loanApplication.service;

import com.example.blms_backend.customer.model.Customer;
import com.example.blms_backend.loanApplication.model.LoanApplication;
import com.example.blms_backend.loanApplication.repository.LoanApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanApplicationService {

    @Autowired
    private LoanApplicationRepository loanApplicationRepo;

    public LoanApplication saveLoanApp(LoanApplication newLoanApp) {
        return loanApplicationRepo.save(newLoanApp);
    }

    public LoanApplication findApplicationById(Integer id) {

        Optional<LoanApplication> loanAppOpt = loanApplicationRepo.findById(id);

        return loanAppOpt.isPresent() ? loanAppOpt.get() : null;
    }

//    public List<LoanApplication> getLoanAppByCustomerId(Integer id) {
//        return loanApplicationRepo.getAllLoanAppByCustomerId(id);
//    }

//    public List<LoanApplication> getLoanAppByLoanProductType(String loanProductName) {
//        return loanApplicationRepo.getAllLoanAppByLoanProductName(loanProductName);
//    }

    public LoanApplication deleteLoanAppById(Integer id) {
        Optional<LoanApplication> loanApp = loanApplicationRepo.findById(id);

        loanApplicationRepo.deleteById(id);

        return loanApp.isPresent() ? loanApp.get() : null;
    }

}
