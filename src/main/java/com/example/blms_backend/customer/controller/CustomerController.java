package com.example.blms_backend.customer.controller;

import com.example.blms_backend.customer.model.Customer;
import com.example.blms_backend.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {
        return ResponseEntity.ok().body(customerService.saveCustomer(customer));
    }

    /*
        {
            "name": "Sanjeeva",
            "email": "duos78550@gmail.com",
            "phone": "8186838273",
            "address": "12A/12 Lincon Street NYC",
            "kycStatus": "PENDING"
        }

     */

    @PutMapping
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
        return ResponseEntity.ok().body(customerService.updateCustomer(customer.getCustomerId(), customer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> deleteCustomerById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(customerService.deleteCustomerById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<Customer> getCustomer(@RequestParam String query) {

        if(query.indexOf('@') != -1) {
            System.out.println("Finding By Email");
            return ResponseEntity.ok().body(customerService.findCustomerByEmail(query));
        } else if (query.matches("([0-9])+")) {
            System.out.println("Finding By ID");
            return ResponseEntity.ok().body(customerService.findCustomerById(Integer.parseInt(query)));
        } else if (query.matches("[a-zA-Z]+")){
            System.out.println("Finding By Name");
            return ResponseEntity.ok().body(customerService.findCustomerByName(query));
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/kyc/verify/{id}")
    public ResponseEntity<Customer> approveKyc(@PathVariable Integer id) {
        return ResponseEntity.ok().body(customerService.verifyKyc(id));
    }

    @GetMapping("/kyc/reset/{id}")
    public ResponseEntity<Customer> rejectKyc(@PathVariable Integer id) {
        return ResponseEntity.ok().body(customerService.resetKyc(id));
    }

}
