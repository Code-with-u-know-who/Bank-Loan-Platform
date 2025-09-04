package com.example.blms_backend.repaymentManagement.service;

import com.example.blms_backend.repaymentManagement.model.Repayment;
import com.example.blms_backend.repaymentManagement.repository.RepaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RepaymentService {

    @Autowired
    private RepaymentRepository repaymentRepo;

    public Repayment saveRepayment(Repayment repaymentObj) {
        return repaymentRepo.save(repaymentObj);
    }

    public Repayment findById(Integer id) {

        Optional<Repayment> repaymentOpt = repaymentRepo.findById(id);

        if(repaymentOpt.isPresent()) {
            return repaymentOpt.get();
        }

        return null;
    }

    public Repayment deleteById(Integer id) {

        Optional<Repayment> repaymentOpt = repaymentRepo.findById(id);

        if(repaymentOpt.isPresent()) {
            repaymentRepo.deleteById(id);
            return repaymentOpt.get();
        }
        return null;
    }

}
