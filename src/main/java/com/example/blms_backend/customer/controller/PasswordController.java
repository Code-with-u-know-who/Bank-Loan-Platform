package com.example.blms_backend.customer.controller;

import com.example.blms_backend.customer.model.Password;
import com.example.blms_backend.customer.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/password")
public class PasswordController {

    @Autowired
    private PasswordService passwordService;

    @PostMapping
    public ResponseEntity<Password> savePassword(@RequestBody Password password) {
        return ResponseEntity.ok().body(passwordService.savePassword(password));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Password> getPassword(@PathVariable Integer id) {
        return ResponseEntity.ok().body(passwordService.findPasswordById(id));
    }

    @PutMapping
    public ResponseEntity<Password> updatePassword(@RequestBody Password password) {
        return ResponseEntity.ok().body(passwordService.updatePassword(password));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Password> deletePasswordById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(passwordService.deletePasswordById(id));
    }
}
