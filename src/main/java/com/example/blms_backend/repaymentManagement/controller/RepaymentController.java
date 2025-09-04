package com.example.blms_backend.repaymentManagement.controller;

import com.example.blms_backend.repaymentManagement.model.Repayment;
import com.example.blms_backend.repaymentManagement.service.RepaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/repayment")
public class RepaymentController {

    @Autowired
    private RepaymentService repaymentService;

    @PostMapping
    public ResponseEntity<Repayment> saveRepayment(@RequestBody Repayment repaymentObj) {
        return ResponseEntity.ok().body(repaymentService.saveRepayment(repaymentObj));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Repayment> getRepaymentById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(repaymentService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Repayment> deleteRepaymentById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(repaymentService.deleteById(id));
    }


}
