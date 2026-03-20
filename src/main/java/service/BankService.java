package com.bankapp.bankingapp.service;

import com.bankapp.bankingapp.model.User;
import com.bankapp.bankingapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bankapp.bankingapp.model.Transaction;
import com.bankapp.bankingapp.repository.TransactionRepository;
import java.time.LocalDateTime;

@Service
public class BankService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    // Deposit
    public User deposit(Long userId, double amount) {
        User user = userRepository.findById(userId).orElseThrow();

        user.setBalance(user.getBalance() + amount);
        userRepository.save(user);

        // Save transaction
        Transaction tx = new Transaction();
        tx.setUserId(userId);
        tx.setType("DEPOSIT");
        tx.setAmount(amount);
        tx.setDate(LocalDateTime.now());
        transactionRepository.save(tx);

        return user;
    }

    // Withdraw
    public User withdraw(Long userId, double amount) {
        User user = userRepository.findById(userId).orElseThrow();

        if (user.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        user.setBalance(user.getBalance() - amount);
        userRepository.save(user);

        // Save transaction
        Transaction tx = new Transaction();
        tx.setUserId(userId);
        tx.setType("WITHDRAW");
        tx.setAmount(amount);
        tx.setDate(LocalDateTime.now());
        transactionRepository.save(tx);

        return user;
    }

    public String transfer(Long fromUserId, Long toUserId, double amount) {

        User fromUser = userRepository.findById(fromUserId).orElseThrow();
        User toUser = userRepository.findById(toUserId).orElseThrow();

        if (fromUser.getBalance() < amount) {
            return "Insufficient balance";
        }

        // Deduct from sender
        fromUser.setBalance(fromUser.getBalance() - amount);

        // Add to receiver
        toUser.setBalance(toUser.getBalance() + amount);

        userRepository.save(fromUser);
        userRepository.save(toUser);

        return "Transfer successful";
    }
}