package com.example.blms_backend.repaymentManagement.model;

import com.example.blms_backend.loanApplication.model.LoanApplication;
import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class Repayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer repaymentId;

    @ManyToOne
    @JoinColumn(name = "applicationId")
    private LoanApplication loanApplication;

    private Date dueDate;

    private Double amountDue;

    private Date paymentDate;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    public Integer getRepaymentId() {
        return repaymentId;
    }

    public LoanApplication getLoanApplication() {
        return loanApplication;
    }

    public void setLoanApplication(LoanApplication loanApplication) {
        this.loanApplication = loanApplication;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Double getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(Double amountDue) {
        this.amountDue = amountDue;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
