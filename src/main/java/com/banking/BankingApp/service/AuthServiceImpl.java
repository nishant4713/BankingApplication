package com.banking.BankingApp.service;

import com.banking.BankingApp.dtos.AuthResponse;
import com.banking.BankingApp.dtos.LoginRequest;
import com.banking.BankingApp.dtos.RegisterRequest;
import com.banking.BankingApp.entity.Account;
import com.banking.BankingApp.entity.User;
import com.banking.BankingApp.enums.Roles;
import com.banking.BankingApp.exception.CustomException;
import com.banking.BankingApp.repository.AccountRepository;
import com.banking.BankingApp.repository.UserRepository;
import com.banking.BankingApp.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Random;
@Service
public class AuthServiceImpl implements AuthService{
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    AccountRepository accountRepository;



    @Override
    public void register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new CustomException("Email already in use");
        }

        if (userRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new CustomException("Phone number already in use");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setFullName(request.getFullName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setAddress(request.getAddress());
        user.setEnabled(true);
        user.setRole(Roles.USER);

        Account account = new Account();
        account.setAccountNumber(generateAccountNumber());
        account.setAccountType("CHECKING");
        account.setBalance(0.0);

        user.setAccount(account);
        account.setUser(user);
        System.out.println("Saving user: " + user.getEmail());
        System.out.println("Account number: " + user.getAccount().getAccountNumber());
        System.out.println("Balance: " + user.getAccount().getBalance());
        System.out.println("User ID (before save): " + user.getId()); // should be null
        System.out.println("Account ID (before save): " + user.getAccount().getId()); // should be null
        userRepository.save(user);

    }

    @Override
    public AuthResponse login(LoginRequest request) {
       authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
       String jwt = jwtUtils.generateToken(request.getEmail());
       return new AuthResponse(jwt,"Login successful");
    }
    private Long generateAccountNumber() {
        Random random = new Random();
        return 1000000000L + random.nextLong(900000000);
    }

}
