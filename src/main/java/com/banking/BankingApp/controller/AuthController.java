package com.banking.BankingApp.controller;

import com.banking.BankingApp.dtos.LoginRequest;
import com.banking.BankingApp.dtos.RegisterRequest;
import com.banking.BankingApp.repository.UserRepository;
import com.banking.BankingApp.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
     @Autowired
     AuthService userService;

     @Autowired
    AuthenticationManager authenticationManager;

     @Autowired
    UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request){

            userService.register(request);
            return ResponseEntity.ok().body("User Registered Successfully");
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(userService.login(request));
    }
}