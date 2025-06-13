package com.banking.BankingApp.repository;

import com.banking.BankingApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByPhoneNumber(Long phoneNumber);
    Optional<User> findByAccountNumber(Long accountNumber);

    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(Long phoneNumber);
    boolean existsByAccountNumber(Long accountNumber);

}
