package com.example.blms_backend.loanProduct.model;

import com.example.blms_backend.loanApplication.model.LoanApplication;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class LoanProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer loanProductId;

    private String productName;

    private Double interestRate;

    private Double minAmount;

    private Double maxAmount;

    private Integer tenure;

    public Integer getLoanProductId() {
        return loanProductId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public Double getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(Double minAmount) {
        this.minAmount = minAmount;
    }

    public Double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(Double maxAmount) {
        this.maxAmount = maxAmount;
    }

    public Integer getTenure() {
        return tenure;
    }

    public void setTenure(Integer tenure) {
        this.tenure = tenure;
    }

}
