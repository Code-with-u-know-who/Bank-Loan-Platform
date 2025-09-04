package com.example.blms_backend.customer.service;

import com.example.blms_backend.customer.model.Password;
import com.example.blms_backend.customer.repository.PasswordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PasswordService {

    @Autowired
    private PasswordRepository passwordRepo;

    public Password savePassword(Password passwordObj) {
        return passwordRepo.save(passwordObj);
    }

    public Password updatePassword(Password newPasswordObj) {
        return passwordRepo.save(newPasswordObj);
    }

    public Password findPasswordById(Integer id) {
        Optional<Password> passwordOpt = passwordRepo.findById(id);

        if(passwordOpt.isPresent()) {
            return passwordOpt.get();
        }
        return null;
    }

    public Password deletePasswordById(Integer id) {
        Optional<Password> passwordOpt = passwordRepo.findById(id);

        if(passwordOpt.isPresent()) {
            passwordRepo.deleteById(id);
            return passwordOpt.get();
        }
        return null;
    }

    public Password findByPassword(String pass) {return passwordRepo.findByPasswordChars(pass);}

}
