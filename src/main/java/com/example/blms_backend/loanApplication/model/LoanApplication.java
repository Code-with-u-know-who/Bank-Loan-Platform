package com.example.blms_backend.loanApplication.model;

import com.example.blms_backend.customer.model.Customer;
import com.example.blms_backend.loanProduct.model.LoanProduct;
import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class LoanApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer applicationId;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "loanProductId")
    private LoanProduct loanProduct;

    private double loanAmount;

    private Date applicationDate;

    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus;

    public Integer getApplicationId() {
        return applicationId;
    }

    public LoanProduct getLoanProduct() {
        return loanProduct;
    }

    public void setLoanProductId(LoanProduct loanProduct) {
        this.loanProduct = loanProduct;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public ApprovalStatus getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(ApprovalStatus approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
