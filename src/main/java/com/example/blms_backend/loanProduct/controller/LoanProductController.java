package com.example.blms_backend.loanProduct.controller;

import com.example.blms_backend.loanProduct.model.LoanProduct;
import com.example.blms_backend.loanProduct.service.LoanProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/loanProduct")
public class LoanProductController {

    @Autowired
    private LoanProductService loanProductService;

    @PostMapping
    public ResponseEntity<LoanProduct> saveLoanProduct(@RequestBody LoanProduct loanProduct) {
        System.out.println("Method Called");
        return ResponseEntity.ok().body(loanProductService.saveLoanProduct(loanProduct));
    }

    /*
    {
        "productName": "Bike Loan",
        "interestRate": 23.02,
        "minAmount": 1210.1,
        "maxAmount": 1232.3,
        "tenure": 20
    }
     */

    @GetMapping("/{id}")
    public ResponseEntity<LoanProduct> getLoanProductById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(loanProductService.findLoanProductById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<LoanProduct> deleteLoanProductById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(loanProductService.deleteLoanProduct(id));
    }

}
