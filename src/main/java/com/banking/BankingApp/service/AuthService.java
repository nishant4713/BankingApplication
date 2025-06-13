package com.banking.BankingApp.service;

import com.banking.BankingApp.dtos.AuthResponse;
import com.banking.BankingApp.dtos.LoginRequest;
import com.banking.BankingApp.dtos.RegisterRequest;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    public void register(RegisterRequest request);
    public AuthResponse login(LoginRequest request);
}
