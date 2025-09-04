package com.example.blms_backend.loanApplication.controller;

import com.example.blms_backend.loanApplication.model.LoanApplication;
import com.example.blms_backend.loanApplication.service.LoanApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loanApp")
public class LoanApplicationController {

    @Autowired
    private LoanApplicationService loanAppService;

    @PostMapping
    public ResponseEntity<LoanApplication> saveLoanApp(@RequestBody LoanApplication loanApp) {
        return ResponseEntity.ok().body(loanAppService.saveLoanApp(loanApp));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanApplication> getLoanAppById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(loanAppService.findApplicationById(id));
    }

//    @GetMapping("/customer/{customerId}")
//    public ResponseEntity<List<LoanApplication>> getLoanAppsByCustomerId(@PathVariable Integer customerId) {
//        return ResponseEntity.ok().body(loanAppService.getLoanAppByCustomerId(customerId));
//    }

//    @GetMapping("/product/{productName}")
//    public ResponseEntity<List<LoanApplication>> getLoanAppsByProductName(@PathVariable String productName) {
//        return ResponseEntity.ok().body(loanAppService.getLoanAppByLoanProductType(productName));
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<LoanApplication> deleteCustomerById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(loanAppService.deleteLoanAppById(id));
    }

}
