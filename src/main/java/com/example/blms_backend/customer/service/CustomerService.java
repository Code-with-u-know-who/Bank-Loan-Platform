package com.example.blms_backend.customer.service;

import com.example.blms_backend.customer.model.Customer;
import com.example.blms_backend.customer.model.KycStatus;
import com.example.blms_backend.customer.repository.CustomerRepository;
import com.example.blms_backend.loanApplication.model.LoanApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Integer id, Customer updatedCustomer) {
        Optional<Customer> existingCustomerOpt = customerRepository.findById(updatedCustomer.getCustomerId());

        if(existingCustomerOpt.isPresent()) {
            Customer existingCustomer = existingCustomerOpt.get();

            existingCustomer.setName(updatedCustomer.getName());
            existingCustomer.setEmail(updatedCustomer.getEmail());
            existingCustomer.setPhone(updatedCustomer.getPhone());
            existingCustomer.setAddress(updatedCustomer.getAddress());
            existingCustomer.setKycStatus(updatedCustomer.getKycStatus());

            return customerRepository.save(existingCustomer);
        }
        return customerRepository.save(updatedCustomer);
    }


    public List<Customer> getCustomersByKycStatus(KycStatus kycStatus) {
        return customerRepository.findByKycStatus(kycStatus);
    }

//    public List<Customer> searchCustomersByName(String name) {
//        return customerRepository.findByNameContainingIgnoreCase(name);
//    }

    public Customer findCustomerById(Integer id) {
        return customerRepository.findById(id).orElse(null);
    }

    public Customer findCustomerByName(String name) {
        Optional<Customer> customer = customerRepository.findByName(name);

        return customer.isPresent() ? customer.get(): null;
    }

    public Customer findCustomerByEmail(String email) {
        Optional<Customer> customer = customerRepository.findByEmail(email);

        return customer.isPresent() ? customer.get(): null;
    }

    public Customer deleteCustomerById(Integer id) {

        Optional<Customer> customerOpt = customerRepository.findById(id);

        if(customerOpt.isPresent()) {
            customerRepository.deleteById(id);
            return customerOpt.get();
        }

        return null;
    }

    // KYC
    public Customer verifyKyc(Integer id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            customer.setKycStatus(KycStatus.VERIFIED);
            return customerRepository.save(customer);
        }
        return null;
    }

    public Customer resetKyc(Integer id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            customer.setKycStatus(KycStatus.PENDING);
            return customerRepository.save(customer);
        }
        return null;
    }

}
