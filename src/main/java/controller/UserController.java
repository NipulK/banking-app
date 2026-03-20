package com.bankapp.bankingapp.controller;

import com.bankapp.bankingapp.model.User;
import com.bankapp.bankingapp.repository.UserRepository;
import com.bankapp.bankingapp.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @PostMapping("/login")
    public User login(@RequestBody User user) {

        User existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser == null) {
            return null;
        }

        if (!existingUser.getPassword().equals(user.getPassword())) {
            return null;
        }

        return existingUser;
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BankService bankService;   // ✅ FIXED

    // Create user
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    // Get all users
    @GetMapping
    public java.util.List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Deposit
    @PostMapping("/deposit")
    public User deposit(@RequestParam Long userId, @RequestParam double amount) {
        return bankService.deposit(userId, amount);
    }

    // Withdraw
    @PostMapping("/withdraw")
    public User withdraw(@RequestParam Long userId, @RequestParam double amount) {
        return bankService.withdraw(userId, amount);
    }

    @PostMapping("/transfer")
    public String transfer(@RequestParam Long fromUserId,
                           @RequestParam Long toUserId,
                           @RequestParam double amount) {
        return bankService.transfer(fromUserId, toUserId, amount);
    }

}